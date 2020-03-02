package com.mingzhang.repo.annotation1.dao.impl.dao;

import com.mingzhang.repo.annotation1.PeopleAnnotion;
import com.mingzhang.repo.annotation1.dao.IPeople;

/**
 * File Description:
 *
 * @author MingZhang                      --Variety is the spice of life.
 * @date 2020-02-27 17:03
 */
public class WhitePeople implements IPeople {

    @Override
    @PeopleAnnotion(say = "White")
    public void say() {
        System.out.println("I am White");
    }

}