package com.mingzhang.repo.annotation1.dao.impl;

import com.mingzhang.repo.annotation1.PeopleAnnotion;
import com.mingzhang.repo.annotation1.dao.IPeople;

/**
 * File Description:
 *
 * @author MingZhang                      --Variety is the spice of life.
 * @date 2020-02-27 17:04
 */
public class BlackPeople implements IPeople {

    @Override
    @PeopleAnnotion(say = "Black")
    public void say() {
        System.out.println("I am Black");
    }

}
