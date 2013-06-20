package com.afjk01.tool.MultiPointerServer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

// 強制的に利用。
// プロパティ→Javaコンパイラ→エラー／警告
// 「プロジェクト固有の設定を可能にする」にチェック
// 「使用すべきでない制限されたAPI」→「禁止された参照」を「無視」に。
import com.sun.awt.AWTUtilities;


public class PointerFrame extends JDialog
{
	public int mX;
	public int mY;
	private Dimension mScreenSize;
	JLabel mLable;
	
	public PointerFrame()
	{
		super();
	}
	/*
	public PointerFrame( String Title )
	{
		super( Title );
	}*/
	
	public PointerFrame( int x, int y )
	{
		super();
		this.mX = x;
		this.mY = y;
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

//		mLable = new JLabel("setWindowOpaque()");
//		mLable.setForeground(Color.black);
//		mLable.setPreferredSize(new Dimension(100,100));
//	    getContentPane().add(mLable);
	    
	    // マウスカーソル画像のセット
	    JLabel label = new JLabel( new ImageIcon("./res/pointer.png") );
	    getContentPane().add(label);
	    

		setVisible(true);
		setBounds(mX, mY, 20, 20);
	}

	public void move( int x, int y )
	{
		mX += x;
		mY += y;
		if( mX < 0 )
		{
			mX = 0;
		}
		if( mY < 0 )
		{
			mY = 0;
		}
		if( mScreenSize != null )
		{
			if( mX > mScreenSize.width )
			{
				mX = mScreenSize.width -1 ;
			}
			if( mY > mScreenSize.height )
			{
				mY = mScreenSize.height -1 ;
			}
		}
	}
	public void showPointer()
	{
		setBounds(mX-4, mY-1, 20, 20);
	}
	
	public void showPointer( int x, int y)
	{
		mX = x;
		mY = y;
		showPointer();
	}
}
