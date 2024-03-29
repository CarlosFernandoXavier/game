package game;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.ImageIcon;

public class WinchesterFactory implements BulletFactory{

    @Override
    public Bullet createBullet() {
        Bullet bullet = new Bullet();
        ImageIconTarget image = new OpenIconAdapter();
        ImageIcon imageIcon = image.carregarIcon("images/lu.png");
        bullet.setX(0);
        bullet.setY(0);
        bullet.setImgBullet(imageIcon);
        bullet.setHeight(imageIcon.getIconHeight());
        bullet.setWidth(imageIcon.getIconWidth());
        bullet.setFired(false);
        return bullet;
    }
    
}
