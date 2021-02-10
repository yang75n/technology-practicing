package com.yqw.designPatterns.behavioral.observer;

public abstract class Observer {
   protected Subject subject;
   public abstract void update();
}