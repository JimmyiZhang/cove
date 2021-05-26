package plus.cove.jazzy.api.test.concurrent;

public class CustomerThreadTask implements Runnable {
    private String name;

    public CustomerThreadTask(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.toString() + " is running!");
            Thread.sleep(3000); //让任务执行慢点
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "customer task [name=" + name + "]";
    }
}
