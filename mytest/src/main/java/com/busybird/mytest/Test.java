package com.busybird.mytest;

/**
 * Created by wusihai on 2016/11/7.
 */

public class Test {
    static int ID = 0;

    public static void main(String[] args)
    {

        FoodShop foodShop = new FoodShop();
        Producer producer = new Producer(foodShop);
        Consumer consumer = new Consumer(foodShop);

        new Thread(producer,"生产者1").start();
        new Thread(producer,"生产者2").start();
        new Thread(consumer,"消费者1").start();
        new Thread(consumer,"消费者2").start();

    }
}

class FoodShop
{
    Food[] foods = new Food[20];
    int index;

    public synchronized void push(Food food)
    {
        while(index >= foods.length)
        {
            try
            {
                this.wait();
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        foods[index] = food;
        index++;
        this.notify();

    }

    public synchronized Food pop()
    {
        while(index <= 0)
        {
            try
            {
                this.wait();
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        index--;
        Food food = foods[index];
        foods[index] = null;
        this.notify();
        return food;
    }

}


class Producer implements Runnable
{
    private FoodShop foodShop;

    public Producer(FoodShop foodShop)
    {
        this.foodShop = foodShop;
    }

    @Override
    public void run()
    {
        for (int i = 0; i < 100; i++)
        {
            Food food = new Food(Test.ID);
            Test.ID++;
            foodShop.push(food);
            System.out.println(Thread.currentThread().getName() + "生产了一个食物：" + food.toString());
            try
            {
                Thread.sleep(500);
            } catch (InterruptedException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}

class Consumer implements Runnable
{
    private FoodShop foodShop;

    public Consumer(FoodShop foodShop)
    {
        this.foodShop = foodShop;
    }

    @Override
    public void run()
    {
        for (int i = 0; i < 100; i++)
        {
            Food pop = foodShop.pop();
            System.out.println(Thread.currentThread().getName() +"购买了一个食物：" + pop.toString());
        }
    }

}
class Food
{
    int ID;

    public Food(int iD)
    {
        super();
        ID = iD;
    }

    public int getID()
    {
        return ID;
    }

    public void setID(int iD)
    {
        ID = iD;
    }

    @Override
    public String toString()
    {
        return "Food [ID=" + ID + "]";
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ID;
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Food other = (Food) obj;
        if (ID != other.ID)
            return false;
        return true;
    }



}
