package com.afjk01.tool.MultiPointerServer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

// 強制的に利用。
// プロパティ→Javaコンパイラ→エラー／警告
// 「プロジェクト固有の設定を可能にする」にチェック
// 「使用すべきでない制限されたAPI」→「禁止された参照」を「無視」に。
import com.sun.awt.AWTUtilities;


public class ScreenKeyboard extends JFrame//JDialog
{
	private Dimension mScreenSize;
	private int mImageWidth;
	private int mImageHeight;

	public ScreenKeyboard()
	{
		super();
	    setAlwaysOnTop(true);
	    
	    Toolkit tk = getToolkit();
	    mScreenSize = tk.getScreenSize();

	    setUndecorated(true);							// タイトルバーとかを消す。setWindowOpaqueおよびsetVisibileより前に呼ぶ。
	    // 単純透過ウィンドウ
		//AWTUtilities.setWindowOpacity(this, (Float)0.70f);	// 透明率の設定。
	    
	    // ピクセル単位透過ウィンドウ
	    // Ubuntu では、AWTUtilitiesを使用すると下記Exceptionが発生することを確認。
	    // java.lang.ClassNotFoundException:
	    AWTUtilities.setWindowOpaque(this, false);	// 完全透明。マウスイベントはウィンドウに通知されない。

		GPanel gpanel = new GPanel();
		gpanel.setOpaque( false );
	    
	    // キーボード画像のセット
	    ImageIcon keyboardImage = new ImageIcon("./res/KeyBoardImage.png");
	    JLabel label = new JLabel( keyboardImage );
	    //getContentPane().add(label);
	    gpanel.add( label );
	    
	    this.add( gpanel, BorderLayout.CENTER);
	    
	    mImageWidth = keyboardImage.getIconWidth();
	    mImageHeight = keyboardImage.getIconHeight();
	    
		setVisible(true);
		setBounds( ( mScreenSize.width - mImageWidth ) / 2 , mScreenSize.height / 2, mImageWidth, mImageHeight );
		
	}
}

class GPanel extends JPanel 
{
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    int w = this.getWidth();
    int h = this.getHeight();
    g.setColor(Color.RED);
//    g.fillOval(0, 0, w, h * 2);
    g.setColor(Color.BLUE);
    g.setFont(new Font("Serif", Font.BOLD, 36));
    g.drawString("Let's Swing!", 10, h - 50);
  }
}
