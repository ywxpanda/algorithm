package com.panda.tree;

import java.util.List;

public class MaxHappy {

    static class Employee {
        int happy;
        List<Employee> juniors;
    }

    static class MaxInfo {
        //来的最大happy
        private final int yes;
        //不来的最大happy
        private final int no;

        MaxInfo(int yes, int no) {
            this.yes = yes;
            this.no = no;
        }
    }


    public static int maxHappy(Employee employee) {
        if (employee==null){
            return 0;
        }
        MaxInfo happy = happy(employee);
        return Math.max(happy.yes, happy.no);
    }

    private static MaxInfo happy(Employee employee) {
        if (employee.juniors.isEmpty()) {
            return new MaxInfo(employee.happy, 0);
        }
        int yes = employee.happy;
        int no = 0;
        for (Employee junior : employee.juniors) {
            MaxInfo juniorHappy = happy(junior);
            //当前来了，下级就不能来
            yes += juniorHappy.no;
            //当前不了，下级来与不来的最大值之和
            no += Math.max(juniorHappy.no, juniorHappy.yes);
        }

        return new MaxInfo(yes, no);

    }
}
