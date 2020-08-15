运行参数和API列表介绍
QLExpressRunner如下图所示，从语法树分析、上下文、执行过程三个方面提供二次定制的功能扩展。
1、属性开关
isPrecise
	/**
	 * 是否需要高精度计算
	 */
	private boolean isPrecise = false;
高精度计算在会计财务中非常重要，java的float、double、int、long存在很多隐式转换，做四则运算和比较的时候其实存在非常多的安全隐患。 所以类似汇金的系统中，会有很多BigDecimal转换代码。而使用QLExpress，你只要关注数学公式本身 订单总价 = 单价 * 数量 + 首重价格 + （ 总重量 - 首重） * 续重单价 ，然后设置这个属性即可，所有的中间运算过程都会保证不丢失精度。

isShortCircuit
	/**
	 * 是否使用逻辑短路特性
	 */
	private boolean isShortCircuit = true;
在很多业务决策系统中，往往需要对布尔条件表达式进行分析输出，普通的java运算一般会通过逻辑短路来减少性能的消耗。例如规则公式： star>10000 and shoptype in('tmall','juhuasuan') and price between (100,900) 假设第一个条件 star>10000 不满足就停止运算。但业务系统却还是希望把后面的逻辑都能够运算一遍，并且输出中间过程，保证更快更好的做出决策。

参照单元测试:ShortCircuitLogicTest.java

isTrace
	/**
	 * 是否输出所有的跟踪信息，同时还需要log级别是DEBUG级别
	 */
	private boolean isTrace = false;
这个主要是是否输出脚本的编译解析过程，一般对于业务系统来说关闭之后会提高性能。

2、调用入参
/**
 * 执行一段文本
 * @param expressString 程序文本
 * @param context 执行上下文，可以扩展为包含ApplicationContext
 * @param errorList 输出的错误信息List
 * @param isCache 是否使用Cache中的指令集,建议为true
 * @param isTrace 是否输出详细的执行指令信息，建议为false
 * @param aLog 输出的log
 * @return
 * @throws Exception
 */
	Object execute(String expressString, IExpressContext<String,Object> context,List<String> errorList, boolean isCache, boolean isTrace, Log aLog);
3、功能扩展API列表
QLExpress主要通过子类实现Operator.java提供的以下方法来最简单的操作符定义，然后可以被通过addFunction或者addOperator的方式注入到ExpressRunner中。

	public abstract Object executeInner(Object[] list) throws Exception;
比如我们几行代码就可以实现一个功能超级强大、非常好用的join操作符:

list = 1 join 2 join 3; -> [1,2,3] list = join(list,4,5,6); -> [1,2,3,4,5,6]

public class JoinOperator extends Operator{
	public Object executeInner(Object[] list) throws Exception {
        java.util.List result = new java.util.ArrayList();
        Object opdata1 = list[0];
        if(opdata1 instanceof java.util.List){
           result.addAll((java.util.List)opdata1);
        }else{
            result.add(opdata1);
        }
        for(int i=1;i<list.length;i++){
           result.add(list[i]);
        }
        return result;
	}
}
如果你使用Operator的基类OperatorBase.java将获得更强大的能力，基本能够满足所有的要求。

（1）function相关API
//通过name获取function的定义
OperatorBase getFunciton(String name);

//通过自定义的Operator来实现类似：fun(a,b,c)
void addFunction(String name, OperatorBase op);
//fun(a,b,c) 绑定 object.function(a,b,c)对象方法
void addFunctionOfServiceMethod(String name, Object aServiceObject,
			String aFunctionName, Class<?>[] aParameterClassTypes,
			String errorInfo);
//fun(a,b,c) 绑定 Class.function(a,b,c)类方法
void addFunctionOfClassMethod(String name, String aClassName,
			String aFunctionName, Class<?>[] aParameterClassTypes,
			String errorInfo);
//给Class增加或者替换method，同时 支持a.fun(b) ，fun(a,b) 两种方法调用
//比如扩展String.class的isBlank方法:“abc”.isBlank()和isBlank("abc")都可以调用
void addFunctionAndClassMethod(String name,Class<?>bindingClass, OperatorBase op);
（2）Operator相关API
提到脚本语言的操作符，优先级、运算的目数、覆盖原始的操作符(+,-,*,/等等)都是需要考虑的问题，QLExpress统统帮你搞定了。

	//添加操作符号,可以设置优先级
void addOperator(String name,Operator op);
void addOperator(String name,String aRefOpername,Operator op);
	
	//替换操作符处理
OperatorBase replaceOperator(String name,OperatorBase op);
    
  //添加操作符和关键字的别名，比如 if..then..else -> 如果。。那么。。否则。。
void addOperatorWithAlias(String keyWordName, String realKeyWordName,
			String errorInfo);
（3）宏定义相关API
QLExpress的宏定义比较简单，就是简单的用一个变量替换一段文本，和传统的函数替换有所区别。

//比如addMacro("天猫卖家","userDO.userTag &1024 ==1024")
void addMacro(String macroName,String express) 
（4）java class的相关api
QLExpress可以通过给java类增加或者改写一些method和field，比如 链式调用："list.join("1").join("2")"，比如中文属性："list.长度"。

//添加类的属性字段
void addClassField(String field,Class<?>bindingClass,Class<?>returnType,Operator op);

//添加类的方法
void addClassMethod(String name,Class<?>bindingClass,OperatorBase op);
注意，这些类的字段和方法是执行器通过解析语法执行的，而不是通过字节码增强等技术，所以只在脚本运行期间生效，不会对jvm整体的运行产生任何影响，所以是绝对安全的。

（4）语法树解析变量、函数的API
这些接口主要是对一个脚本内容的静态分析，可以作为上下文创建的依据，也可以用于系统的业务处理。 比如：计算 “a+fun1(a)+fun2(a+b)+c.getName()” 包含的变量:a,b,c 包含的函数:fun1,fun2

//获取一个表达式需要的外部变量名称列表
String[] getOutVarNames(String express);

String[] getOutFunctionNames(String express);
（5）语法解析校验api
脚本语法是否正确，可以通过ExpressRunner编译指令集的接口来完成。

String expressString = "for(i=0;i<10;i++){sum=i+1}return sum;";
InstructionSet instructionSet = expressRunner.parseInstructionSet(expressString);
//如果调用过程不出现异常，指令集instructionSet就是可以被加载运行（execute）了！
（6）指令集缓存相关的api
因为QLExpress对文本到指令集做了一个本地HashMap缓存，通常情况下一个设计合理的应用脚本数量应该是有限的，缓存是安全稳定的，但是也提供了一些接口进行管理。

	//优先从本地指令集缓存获取指令集，没有的话生成并且缓存在本地
	InstructionSet getInstructionSetFromLocalCache(String expressString);
	//清除缓存
	void clearExpressCache();
（7）安全风险控制
7.1 防止死循环
    try {
    	express = "sum=0;for(i=0;i<1000000000;i++){sum=sum+i;}return sum;";
	//可通过timeoutMillis参数设置脚本的运行超时时间:1000ms
	Object r = runner.execute(express, context, null, true, false, 1000);
	System.out.println(r);
	throw new Exception("没有捕获到超时异常");
    } catch (QLTimeOutException e) {
	System.out.println(e);
    }
7.1 防止调用不安全的系统api
    ExpressRunner runner = new ExpressRunner();
    QLExpressRunStrategy.setForbiddenInvokeSecurityRiskMethods(true);
    
    DefaultContext<String, Object> context = new DefaultContext<String, Object>();
    try {
    	express = "System.exit(1);";
	Object r = runner.execute(express, context, null, true, false);
	System.out.println(r);
	throw new Exception("没有捕获到不安全的方法");
    } catch (QLException e) {
	System.out.println(e);
    }
（8）增强上下文参数Context相关的api
8.1 与spring框架的无缝集成
上下文参数 IExpressContext context 非常有用，它允许put任何变量，然后在脚本中识别出来。

在实际中我们很希望能够无缝的集成到spring框架中，可以仿照下面的例子使用一个子类。

public class QLExpressContext extends HashMap<String, Object> implements
		IExpressContext<String, Object> {

	private ApplicationContext context;

	//构造函数，传入context和 ApplicationContext
	public QLExpressContext(Map<String, Object> map,
                            ApplicationContext aContext) {
		super(map);
		this.context = aContext;
	}

	/**
	 * 抽象方法：根据名称从属性列表中提取属性值
	 */
	public Object get(Object name) {
		Object result = null;
		result = super.get(name);
		try {
			if (result == null && this.context != null
					&& this.context.containsBean((String) name)) {
				// 如果在Spring容器中包含bean，则返回String的Bean
				result = this.context.getBean((String) name);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	public Object put(String name, Object object) {
		return super.put(name, object);
	}

}
完整的demo参照 SpringDemoTest.java

8.2 自定义函数操作符获取原始的context控制上下文
自定义的Operator需要直接继承OperatorBase，获取到parent即可，可以用于在运行一组脚本的时候，直接编辑上下文信息，业务逻辑处理上也非常有用。


public class ContextMessagePutTest {
    
    
    class OperatorContextPut extends OperatorBase {
        
        public OperatorContextPut(String aName) {
            this.name = aName;
        }
    
        @Override
        public OperateData executeInner(InstructionSetContext parent, ArraySwap list) throws Exception {
            String key = list.get(0).toString();
            Object value = list.get(1);
            parent.put(key,value);
            return null;
        }
    }
    
    @Test
    public void test() throws Exception{
        ExpressRunner runner = new ExpressRunner();
        OperatorBase op = new OperatorContextPut("contextPut");
        runner.addFunction("contextPut",op);
        String exp = "contextPut('success','false');contextPut('error','错误信息');contextPut('warning','提醒信息')";
        IExpressContext<String, Object> context = new DefaultContext<String, Object>();
        context.put("success","true");
        Object result = runner.execute(exp,context,null,false,true);
        System.out.println(result);
        System.out.println(context);
    }
}