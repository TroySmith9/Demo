package designParttern.decorator;

/**
 * Created by yiban on 2017/3/18.
 */
public class ClientTest2 {

    public static void main(String[] args) {
        Weapon w = new Gun();
        Musket musket = new Musket(w);
        musket.shoot();

        WaterGun waterGun = new WaterGun(musket);
        waterGun.shoot();
        
        MachineGun machineGun=new MachineGun(musket);
        machineGun.shoot();
    }


}
