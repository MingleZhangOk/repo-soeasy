def get_path(filename):
    y_t = np.loadtxt(filename)
    peolpex = int(y_t[0][0])
    peolpey = int(y_t[0][1])
    firex = int(y_t[1][0])
    firey = int(y_t[1][1])
    answer = getQ(peolpex, peolpey, firex, firey)
    return answer
if __name__ == "__main__":
    filename = sys.argv[1]
    # print(filename)
    # root = Tk()
    # canvas = Canvas(root, bg="white")
    # canvas.pack()
    # colors = ['red', 'orange',  'green', 'black','yellow','white','pink']
    result = get_path(filename)
    # with open(filename, 'w') as f:
    #     f.write(result)
    print result
