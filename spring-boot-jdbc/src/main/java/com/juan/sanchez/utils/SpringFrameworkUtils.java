package com.juan.sanchez.utils;

import org.springframework.context.ApplicationContext;

public class SpringFrameworkUtils {

    public static void beansDefinitionsReport(ApplicationContext ctx) {
        System.out.println("");
        System.out.println("-------------------------------------");
        System.out.println("Report - 'Spring ApplicationContext'");
        System.out.println("-------------------------------------");
        System.out.println("");
        System.out.println("Number of Beans: " + ctx.getBeanDefinitionCount());
        System.out.println("Definitions of Beans: ");
        for(String beanName : ctx.getBeanDefinitionNames()) {
            System.out.println(" " + beanName);
        }
    }

    public static void profilesAndBeansDefinitionsReport(ApplicationContext ctx) {
        System.out.println("");
        System.out.println("-------------------------------------");
        System.out.println("Report - 'Spring ApplicationContext'");
        System.out.println("-------------------------------------");
        System.out.println("");
        String[] defaultProfiles = ctx.getEnvironment().getDefaultProfiles();
        System.out.println("Default Profiles: " + defaultProfiles.length);
        if (defaultProfiles.length > 0) {
            for (String dp : defaultProfiles) {
                System.out.println(" " + dp);
            }
        } else {
            System.out.println(" -");
        }

        String[] activeProfiles = ctx.getEnvironment().getActiveProfiles();
        System.out.println("Active Profiles: " + activeProfiles.length);

        if (activeProfiles.length > 0) {
            for (String ap : activeProfiles) {
                System.out.println(" " + ap);
            }
        } else {
            System.out.println(" -");
        }

        System.out.println("Amount of Beans: " + ctx.getBeanDefinitionCount());
        System.out.println("Definitions of Beans: ");

        for (String beanName : ctx.getBeanDefinitionNames()) {
            System.out.println(" " + beanName);
        }
    }

    private SpringFrameworkUtils() {

    }

}
