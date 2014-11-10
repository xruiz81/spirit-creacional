/**
 * Copyright (c) 2000, noLimits Technologies 
 * All rights reserved.
 * http://www.nolimits.ro
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * Redistributions of source code must retain the above copyright 
 * notice, this listof conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 *
 * Neither name of the noLimits Technologies nor the names of its 
 * contributors may be used to endorse or promote products derived
 * from this software without specific prior written permission. 
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * ``AS IS'' AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 * FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 * REGENTS OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, 
 * INCIDENTAL, SPECIAL, EXEMPLARY,OR CONSEQUENTIAL DAMAGES 
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF 
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * $Id: DrawTree.java,v 1.1 2014/03/28 17:33:31 xrf Exp $ 
 */


package com.spirit.util.tree;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.PopupMenu;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.JScrollBar;

/**
 * The DrawTree class.
 *
 * This class is generic framework to browser any hierarhical structure.
 *
 * <p>Genericity is obtained throuth the use of 'Id': the DrawTree inself
 * does not perform any action in response to user events, but simply
 * forward them as <b>notification</b> to <b>Id</b>.Each item inserted
 * may have its own Id.
 * <p>Any item added in the Tree is displayed with an icon, and a label.
 * When a Id receive a notification on a node, it may change this node, to
 * modify or update its appearance.
 * @author <a href="mailto:crow@nolimits.ro">Crow</a>
 */
public class DrawTree extends Canvas implements AdjustmentListener{
    /**
     * Specifies that the scrollbars should always  be shown
     */
    public static final int SCROLLBARS_ALWAYS   = 0;
    /**
     * Specifies that the scrollbars should be shown only when size
     * of the nodes exceeds the size of DrawTree dimension.
     */
    public static final int SCROLLBARS_ASNEEDED = 1;
    /**
     * Specifies that lest just one node selected in the some time.
     */
    public static final int SINGLE = 0;
    /**
     * Enables a multiple selection of nodes.	
     */
    public static final int MULTIPLE = 1;

    static final int HMARGIN = 5;
    static final int VMARGIN = 5;
    static final int HGAP = 7;
    static final int DXLEVEL = HGAP*2;

    /**
     * Inner class key listener charge of all the node expansion
     * selection and execution.	
     */
    private class TreeKeyListener extends KeyAdapter{
    	/**
    	 * This function send notification to Id.
    	 * is sent, depending on the node's curent state.
    	 * if ENTER or SPACE button is pressed  on leaf node, an <b> Execute </b> notification is sent
    	 * if ENTER or SPACE button is pressed  on non leaf node, an <b> Collapse </b> or <b> Expand </b> notification is sent
    	 * if UP or DOWN button is pressed the user changed position in Tree, a <b> Select </b? notification is sent;
    	 */
	public void keyPressed(KeyEvent e){	
	    TreeNode node;
	    for(int i=0;i<items.size();i++){
		node=(TreeNode)(items.elementAt(i));
		if(node.isSelected()){
		    if((node!=null)&&(node.id!=null)){
			if(node.children==TreeNode.NOCHILD&&((e.getKeyCode()==KeyEvent.VK_RIGHT)||(e.getKeyCode()==KeyEvent.VK_ENTER)||(e.getKeyCode()==KeyEvent.VK_SPACE))){
			    if(node.isNode==TreeNode.NODE)node.id.notifyExpand(DrawTree.this,node);
			    else  if((e.getKeyCode()==KeyEvent.VK_ENTER)||(e.getKeyCode()==KeyEvent.VK_SPACE))node.id.notifyExecute(DrawTree.this,node);
			    i=items.size();
			}
			if(node.children!=TreeNode.NOCHILD&&((e.getKeyCode()==KeyEvent.VK_LEFT)||(e.getKeyCode()==KeyEvent.VK_ENTER)||(e.getKeyCode()==KeyEvent.VK_SPACE))){
			    if(node.isNode==TreeNode.NODE)node.id.notifyCollapse(DrawTree.this,node);
			    else if((e.getKeyCode()==KeyEvent.VK_ENTER)||(e.getKeyCode()==KeyEvent.VK_SPACE))node.id.notifyExecute(DrawTree.this,node);
			    i=items.size();	
			}
			if(e.getKeyCode()==KeyEvent.VK_UP&&i>0){
			    for(int j=i-1;j>=0;j--){
				node=(TreeNode)(items.elementAt(j));
				if(Visibil(node)){
				    node.id.notifySelect(DrawTree.this,node);
				    i=items.size();j=-1;break;
				}
			    }
			}
			if((e.getKeyCode()==KeyEvent.VK_DOWN)&&(i<(items.size()-1))){
			    for(int j=i+1;j<items.size();j++){
				node=(TreeNode)(items.elementAt(j));
				if(Visibil(node)){
				    node.id.notifySelect(DrawTree.this,node);
				    i=items.size();j=items.size();break;
				}
			    }
			}
		    }
		}
	    }
	}
    }

    /**
     * Inner class Mouse listener charge of all the node expansion
     * selection and execution.
     */
    private class TreeMouseListener extends MouseAdapter{
   	private void clickAt(TreeNode node, MouseEvent me){
	    if(node==null)return;
	    int x = me.getX() - HMARGIN;
	    if(node.id==null) return;
	    //node.id.notifyExpand(this, node);
	    if((x>=node.level*DXLEVEL)&&(x<=node.level*DXLEVEL+DXLEVEL)){
		if(node.children!=TreeNode.NOCHILD) node.id.notifyCollapse(DrawTree.this, node);
		//node.id.notifyCollapse(this, node);
		else  node.id.notifyExpand(DrawTree.this,node);
	    }//item selection
	    else if(x>node.level*DXLEVEL+HGAP)node.id.notifySelect(DrawTree.this, node);
      	}
	
      	/**
       	 * This function send notification to Id.
       	 * on MOUSE_DOWN on a label, a <b> Select</b> notification is sent.
       	 * on DOUBLE_CLICK on a label, an <b> Execute </b> nodification sent.
      	 */	
        public void mouseReleased(MouseEvent me){
	    int y = me.getY() - VMARGIN;
	    if(me.isPopupTrigger()){
		TreeNode node = itemAt(y);
		if((node!=null)&&(node.getPopupMenu()!=null)){
		    PopupMenu pm = node.getPopupMenu();add(pm);
    		    if(pm!=null)pm.show(me.getComponent(),me.getX(),me.getY());
		}
	    }else if(me.getClickCount() == 1) clickAt(itemAt(y),me);
	}

        public void mousePressed(MouseEvent me){
	    int y = me.getY() - VMARGIN;
	    if(me.isPopupTrigger()){
		TreeNode node = itemAt(y);
		if((node!=null)&&(node.getPopupMenu()!=null)){
		    PopupMenu pm = node.getPopupMenu();add(pm);
    		    if(pm!=null)pm.show(me.getComponent(),me.getX(),me.getY());
		}
	    }else if(me.getClickCount() == 1) clickAt(itemAt(y),me);
	}
	
	public void mouseClicked(MouseEvent me){
	    int y = me.getY()-VMARGIN;	
	    if(me.getClickCount()>1){
		TreeNode node = itemAt(y);
		if((node!=null)&&(node.id!=null)){
		    if(node.isNode==TreeNode.NODE)
			if(node.children!=TreeNode.NOCHILD) node.id.notifyCollapse(DrawTree.this, node);else node.id.notifyExpand(DrawTree.this,node);
		    else node.id.notifyExecute(DrawTree.this,node);
		}
	    }
	}
    }

    private JScrollBar vscroll;
    private JScrollBar hscroll;
    private int maxwidth = 0;
    private int startx = 0;
    private Color selectColor = SystemColor.activeCaption;
    private Color selectFontColor = SystemColor.activeCaptionText;
    private int scrollbarDisplayStatus = SCROLLBARS_ASNEEDED;
    private boolean hierarchyChanged = true;
    
    /**
     * This vector contains the treenodes.
     */
    protected Vector items;
    
    /**
     * This vector contains selected treenodes.
     */
    protected Vector selection;
    
    /**
     * Specified first node from vector
     */
    protected int topItem = 0;
    /**
     * Indicate the visible node in browser
     */
    protected int visibleItemCount = 50;
    
    /**
     * Indicate selection status of tree. Default is Single.
     */
    protected int selectionStatus = SINGLE;

    /**    
     * Contains font height for curent font
     */
    protected int fontHeight;

    /**
     * Builds a new browser instance.
     *
     * @param root the root node for this hierarchy
     * @param label the label that should be dislayed for this item
     * @param id the id for this node
     * @param isNode specifies is the node is a leaf node
     */
    public DrawTree(Object root, String label, NodeId id, int isNode){
	this();
	initialize(root, label, id, isNode);
    }

    /**
     * Builds a new browser instance.
     *
     * @param root the root node for this hierarchy
     * @param label the label that should be dislayed for this item
     * @param id the id for this node
     * @param icon1 the first icon that must be dispalyed for this item
     * @param icon2 the second icon that must be dispalyed for this item
     * @param isNode specifies is the node is a leaf node
     */
    public DrawTree(Object root, String label, NodeId id, String icon1, String icon2, int isNode){
	this();
	initialize(root, label, id, icon1, icon2, isNode);
    }
    
    /**
     * Builds a new browser instance.
     */
    protected DrawTree(){
	selection = new Vector(1, 1);
	items = new Vector();
	topItem = 0;
	addMouseListener(new TreeMouseListener());
	addKeyListener(new TreeKeyListener());
    }
    
    /**
     * Add specitied node to 'items' vector.
     */
    protected void initialize(Object item,String label, NodeId id, String icon1, String icon2, int isNode){
	items.addElement(new TreeNode(item, label, id, icon1, icon2, isNode, 0));
    }

    /**
     * Add specitied node to 'items' vector.
     */
    protected void initialize(Object item,String label, NodeId id, String icon1, String icon2, int isNode,Object asObj){
	items.addElement(new TreeNode(item, label, id, icon1, icon2, isNode, 0, null));
    }

    
    /**
     * Add specitied node to 'items' vector.
     */
    protected void initialize(Object item,String label, NodeId id, int isNode){
	items.addElement(new TreeNode(item, label, id, isNode ,0, null));
    }
    
    /**
     * Gets preferred size for browser
     */
    public Dimension getPreferredSize() {
	return new Dimension(200, 400);
    }

    /**
     *  Sets the color of a selected node to the spectified color.
     * @param color the color used to paint a selected node	
     */
    public void setSelectionFontColor(Color color) {
	this.selectFontColor = color;
    }

    /**
     * Sets the background color of a selected node to the specified color.
     * @param color the color used to paint the background of a selected node
     */
    public void setSelectionBackgroundColor(Color color) {
	this.selectColor = color;
    }
	
    /**
     * Sets the scollbars display status to the specified status.The default is
     * SCOLLBARS_ALWAYS.
     * @param scollbarDisplayStatus SCOLLBARS_ASNEEDED|SCOLLBARS_ALWAYS
     */
    public void setScrollbarDisplayStatus(int scrollbarDisplayStatus) {
	this.scrollbarDisplayStatus = scrollbarDisplayStatus;
	hierarchyChanged = false;
    }

    /**
     * Checks if the specified node is visible
     * @param node the node to be tested
     */
    public boolean Visibil(TreeNode node){
	if(getParent(node)!=null){
	    if(getParent(node).childinv!=TreeNode.NOCHILD)return false;
	    return Visibil(getParent(node));
	}return true;
    }

    public void changeSize(){
	hierarchyChanged = true;
    }
    
    /**
     * Repaint the Tree
     */ 
    public void paint(Graphics g){
	fontHeight = g.getFontMetrics().getHeight();
	int fontAscent = g.getFontMetrics().getAscent();
	int itemCount = items.size();
	
	Dimension dim = getSize();
	
	int myHeight = dim.height-VMARGIN*2;
	int myWidth = dim.width-HMARGIN*2;
	
 	g.clipRect(HMARGIN, VMARGIN, myWidth, myHeight);
	g.translate(HMARGIN, VMARGIN);
	
	int y = 0;
	int dx, fatherIndex;

	Graphics bg = g.create();
	
	bg.setColor(SystemColor.window);
	bg.fillRect(0,0,myWidth,myHeight);
	
	bg.setColor(selectColor);
	g.setFont(getFont());
	visibleItemCount = 0;
	TreeNode node;
	
	int labelwidth;
	if(hierarchyChanged)maxwidth = 0;
		
	for(int i=0;i<topItem;i++){
	    node = (TreeNode)items.elementAt(i);
	    if(hierarchyChanged){
		dx = node.level * DXLEVEL;
		labelwidth = g.getFontMetrics().stringWidth(node.label);
		maxwidth = Math.max(maxwidth, dx + DXLEVEL + labelwidth);
	    }
        }
	
       	int nitems = myHeight/fontHeight;
       	int ditems = itemCount-topItem;
       	if(ditems<nitems){
	    topItem = Math.max(0, topItem-(nitems-ditems));
	}
        if(myWidth >= maxwidth){
	    startx = - 2;
        } 
	else if (startx+myWidth > maxwidth){
	    startx = (maxwidth - myWidth - 2);
    	}

	for(int k = topItem+1; k < items.size(); k++)if(Visibil((TreeNode)items.elementAt(k)))y+=fontHeight;	

	for(int i = items.size()-1; i >= topItem ; i--){
	    node = (TreeNode)items.elementAt(i);
	    if(Visibil(node)){
		dx = (node.level * DXLEVEL)-startx;
	        if(y <= myHeight){
		    if(node.selected){
			myWidth = g.getFontMetrics().stringWidth(node.label);		
			bg.setColor(selectColor);
		        bg.fillRect(dx+DXLEVEL, y-1,myWidth, fontHeight);
			if(node.activate)g.setColor(new Color(255,0,0));else g.setColor(selectFontColor);		    
			if(node.isNode()==TreeNode.LEAF){
		    	    bg.setColor(node.getLeafColor());
		    	    bg.fillRect(dx,y,10,10);
			}
			else{
		    	    if(node.children!=TreeNode.NOCHILD)//is opened
		    		g.drawImage(node.icon_open, dx, y, this);
		    	    else
		    		g.drawImage(node.icon_close, dx, y, this);
			    
			}g.drawString(node.label, dx + DXLEVEL, y+fontAscent);
		    }
		    else{
		    	if(node.activate)g.setColor(new Color(255,0,0));else g.setColor(SystemColor.controlText);
	    		if(node.isNode()==TreeNode.LEAF){
		    	    bg.setColor(node.getLeafColor());
		    	    bg.fillRect(dx,y,10,10);
			}
			else{
		    	    if(node.children!=TreeNode.NOCHILD)//is opened
		    		g.drawImage(node.icon_open, dx, y, this);
		    	    else
		    		g.drawImage(node.icon_close, dx, y, this);
			    
			}g.drawString(node.label, dx + DXLEVEL, y+fontAscent);
		    }
		    TreeNode father = getParent(node);
		    if(father!=null) fatherIndex = getIndex(father);else fatherIndex=-1;
	    	    if(fatherIndex!=-1){ // draw fancy lines
			int fi = fatherIndex - topItem;
			g.setColor(SystemColor.inactiveCaption);
			g.drawLine(dx - DXLEVEL + HGAP/2, y + fontHeight/2,dx-DXLEVEL + HGAP/2, (fi+1)*fontHeight - 1);		
			g.drawLine(dx - HGAP/2, y + fontHeight/2, dx - DXLEVEL + HGAP/2 , y + fontHeight/2);
			if((node.childinv!=TreeNode.NOCHILD)||(node.children!=TreeNode.NOCHILD)){
			    bg.setColor(SystemColor.window);bg.fillRect(dx-DXLEVEL+HGAP/2-4,y+fontHeight/2-4,8,8);
			    bg.setColor(selectColor);g.drawRect(dx-DXLEVEL+HGAP/2-4,y+fontHeight/2-4,8,8);			       
			    g.drawLine(dx - DXLEVEL + HGAP/2 - 2,y+fontHeight/2  ,dx - DXLEVEL + HGAP/2+2, y+fontHeight/2);
			    if(node.childinv!=TreeNode.NOCHILD)g.drawLine(dx - DXLEVEL + HGAP/2 ,y+fontHeight/2-2,dx - DXLEVEL + HGAP/2,y+fontHeight/2+2);
			}
	    	    }visibleItemCount++;
		}else{ // draw the lines for invisible nodes.
		    TreeNode father = getParent(node);
		    if(father!=null)fatherIndex = getIndex(father);else fatherIndex=-1;
	            if(fatherIndex != -1){
			int fi = fatherIndex - topItem;
			if((fi+1)*fontHeight -1 < myHeight)
			    g.setColor(SystemColor.inactiveCaption);
			g.drawLine(dx - DXLEVEL + HGAP/2, myHeight-1,dx - DXLEVEL + HGAP/2, (fi+1)*fontHeight-1);
	            }
	        }
	        // hscroll
	        if(hierarchyChanged){
	    	    dx = (node.level * DXLEVEL);
	    	    labelwidth = g.getFontMetrics().stringWidth(node.label);
	    	    maxwidth = Math.max(maxwidth, dx + DXLEVEL + labelwidth);
		}y -= fontHeight;
	    }
	}
	// hscroll
	if(hierarchyChanged){
	    for (int i=itemCount; i < items.size(); ++i) {
		node = (TreeNode) items.elementAt(i);
		dx = (node.level * DXLEVEL);
		labelwidth = g.getFontMetrics().stringWidth(node.label);
		maxwidth = Math.max(maxwidth, dx + DXLEVEL + labelwidth);
	    }
	}
	hierarchyChanged = false;
	updateScrollbars();
    }

    /**
     *	This function protected, and gets position of specified nodes in tree.
     */
    protected int getIndex(TreeNode node){
	int x = 0;
	for(int i=0;i<items.size();i++){
	    if(node.equals((TreeNode)items.elementAt(i)))return x;
	    if(Visibil((TreeNode)items.elementAt(i)))x++;
	}return -1;
    }

    /**
     * This function is protected, and gets node from specified location from tree
     */
    protected TreeNode itemAt(int y) {
	for(int i = topItem; ((i<items.size())&&(y>0));i++){
	    TreeNode node = (TreeNode)items.elementAt(i);
	    if(Visibil(node)){
		if(y<fontHeight)return node;
		y -= fontHeight;
	    }
	}return null;
    }

    public void update(Graphics pg){
        Rectangle r = pg.getClipBounds();
        Graphics offgc;
        Image offscreen = null;
        Dimension d = getSize();
        offscreen = ImageCache.getImage(this, d.width, d.height);
        offgc = offscreen.getGraphics();
        if(r!=null){
            offgc.clipRect(r.x, r.y, r.width, r.height);
        }
        offgc.setColor(getBackground());
        offgc.fillRect(0, 0, d.width, d.height);
        offgc.setColor(getForeground());
        paint(offgc);
        pg.drawImage(offscreen, 0, 0, this);
    }

    /**
     * Insert a new node.
     *
     * @param parent the parent node
     * @param item the abstract object this node refers to. may be null.
     * @param id the node id, that will receive notifications for this node
     * @param label the label displayed in the tree
     * @param isNode specified is the node is leaf node
     */
    public void insert(TreeNode parent, Object item, NodeId id, String label, int isNode, Object asObj){
	boolean done;
	int j;
	if(parent==null)throw new IllegalArgumentException("null parent");
	if((id==null)&&(label == null))throw new IllegalArgumentException("non-null item required");
	if(id==null)id = parent.id;
	if(label == null)label = id.toString();
	if(parent.children == TreeNode.NOCHILD) {
	    parent.children = 1;
	} else {
	    parent.children += 1;
	}
	done = false;
	TreeNode node = null;
	int i = items.indexOf(parent)+parent.children;
	for(;(i<items.size()&&((TreeNode)items.elementAt(i)).level>parent.level);i++){}
	items.insertElementAt(node = new TreeNode(item, label, id, isNode, parent.level+1, asObj),i);
	hierarchyChanged = true;
	return;
    }

    /**
     * Insert a new node.
     *
     * @param parent the parent node
     * @param item the abstract object this node refers to. may be null.
     * @param id the node id, that will receive notifications for this node
     * @param label the label displayed in the tree
     * @param icon_open the first icon for this node
     * @param icon_close the second icon for this node
     * @param isNode specified is the node is leaf node
     */
    public void insert(TreeNode parent, Object item, NodeId id, String label, String icon_open, String icon_close, int isNode){
	boolean done;
	int j;
	if(parent==null)throw new IllegalArgumentException("null parent");
	if((id==null)&&(label == null))throw new IllegalArgumentException("non-null item required");
	if(id==null)id = parent.id;
	if(label == null)label = id.toString();
	if(parent.children == TreeNode.NOCHILD) {
	    parent.children = 1;
	} else {
	    parent.children += 1;
	}
	done = false;
	TreeNode node = null;
	int i = items.indexOf(parent)+parent.children;
	for(;(i<items.size()&&((TreeNode)items.elementAt(i)).level>parent.level);i++){}
	items.insertElementAt(node = new TreeNode(item, label, id, icon_open, icon_close, isNode, parent.level+1),i);
	hierarchyChanged = true;
	return;
    }

    /**
     * Insert a new node.
     *
     * @param parent the parent node
     * @param item the abstract object this node refers to. may be null.
     * @param id the node id, that will receive notifications for this node
     * @param label the label displayed in the tree
     * @param icon_open the first icon for this node
     * @param icon_close the second icon for this node
     * @param isNode specified is the node is leaf node
     */
    public void insert(TreeNode parent, Object item, NodeId id, 
		       String label, String icon_open, 
		       String icon_close, int isNode, Object asObj){
	boolean done;
	int j;
	if(parent==null)throw new IllegalArgumentException("null parent");
	if((id==null)&&(label == null))throw new IllegalArgumentException("non-null item required");
	if(id==null)id = parent.id;
	if(label == null)label = id.toString();
	if(parent.children == TreeNode.NOCHILD) {
	    parent.children = 1;
	} else {
	    parent.children += 1;
	}
	done = false;
	TreeNode node = null;
	int i = items.indexOf(parent)+parent.children;
	for(;(i<items.size()&&((TreeNode)items.elementAt(i)).level>parent.level);i++){}
	items.insertElementAt(node = new TreeNode(item, label, id, icon_open, 
						  icon_close, isNode, 
						  parent.level+1, asObj),i);
	hierarchyChanged = true;
	return;
    }




    /**
     * Remove the specified node
     * This simply removes the node, without removing its children.
     * @param node the node to remove	
     */
    public void remove(TreeNode node){
	int ind = items.indexOf(node);
	TreeNode t = null;
	while(ind>=0){
	    t = (TreeNode)items.elementAt(ind);
	    if (t.level >= node.level)ind--;
	    else{
		t.children--;
		break;
	    }
	}items.removeElement(node);
	if(node.selected)unselect(node);
	hierarchyChanged = true;
    }


    /**
     * Removes the specified node and ist children. 
     * @paran node the node to remove
     */
    public void removeBranch(TreeNode node){
	int ist, iend;
	
	ist  = items.indexOf(node)+1;
	iend = items.size(); /* aicea a fost un -1 */

	for(int i = ist; i< iend; i++) {
	    if(((TreeNode)items.elementAt(ist)).level >node.level){
		remove((TreeNode)items.elementAt(ist));
	    }else break;
	}
	remove(node);
	// hscroll
	hierarchyChanged = true;
    }

    public void removeRootNodeChildren(){
    	for(int i = items.size(); i>0 ; --i) {
    		if(i!=1)
    	    	remove((TreeNode)items.elementAt(i-1));
    	}
    	// hscroll
    	hierarchyChanged = true;
    }
    
    
    /**
     * Change the representation of the specified node.
     * hides all the children nodes of 'item', after call 
     * repaint().
     * @param item the node to change
     */
    public synchronized void collapse(TreeNode item){
	TreeNode node = (TreeNode)item;
	if(node.children != TreeNode.NOCHILD) {
	    node.childinv = node.children;
	    node.children = TreeNode.NOCHILD;
	    for(int j = items.indexOf(item)+1;j<items.size();j++){
		TreeNode child = (TreeNode)items.elementAt(j);
		if(child.level>node.level){
		    if(child.selected){
			unselect(child);
		    }
		}
		else{
		    hierarchyChanged = true;
		    return;
		}
	    }
	}
    }

    /**
     *	Sets the selectation status
     * @param status: SINGLE or MULTIPLE
     */
    public void setSelectionStatus(int status) {
	selectionStatus = status;
    }

    /**
     * Gets the selection status. 
     */
    public int getSelectionStatus(){
	return selectionStatus;
    }

    /**
     * Select the specified node.
     * If selection status is SINGLE perviously selected node
     * is unselected.
     * @param node the node to select	
     */
    public void select(TreeNode node) {
	if(node == null) return;
	if(selectionStatus==SINGLE)unselectAll();
	selection.addElement(node);
	node.selected = true;
    }

    /**
     * Unselect the specified node.
     * @param node the node to unselect
     */
    public void unselect(TreeNode node){
	if(node == null) return;
	selection.removeElement(node);
	node.selected = false;
    }
    
    /**
     * Unselect the selected item
     */
    public void unselectAll(){
	for(Enumeration e = selection.elements();e.hasMoreElements();){
	    TreeNode node = (TreeNode)e.nextElement();
	    node.selected = false;
	}
    }
    
    /**
     * Returns an Enumeration of selected items
     */
    public Enumeration selection(){
	return selection.elements();
    }

    private void updateScrollbars(){
    	int max = items.size()+1;
	int myHeight = getSize().height-VMARGIN*2;
	int nr = 0;
	for(int i = 0; i < items.size(); i++) 
		if(Visibil((TreeNode)items.elementAt(i)))
			nr++;
        if(nr>=visibleItemCount&&nr*fontHeight>myHeight){
	    vscroll.setMaximum(max);
	    vscroll.setVisibleAmount(visibleItemCount);
	    vscroll.setVisible(true);
        }else
	    {
		vscroll.setValue(0);
		vscroll.setMaximum(max);
		vscroll.setVisibleAmount(max);
		if(scrollbarDisplayStatus==SCROLLBARS_ASNEEDED)
			vscroll.setVisible(false);
	}
	int myWidth = getSize().width-HMARGIN*2;
	hscroll.setMaximum(maxwidth);
	hscroll.setVisibleAmount(myWidth+2);
	if(maxwidth > myWidth){
	    hscroll.setVisible(true);
	} 
	else{
	    if(scrollbarDisplayStatus == SCROLLBARS_ASNEEDED)hscroll.setVisible(false);
	}
    }
    
    /**
     * Sets 'a' as vertical Scrollbar.
     * The Browser becomes an AdjusmentListener of this scrollbar.
     */
    public void setVerticalScrollbar(JScrollBar a) {
	vscroll = a;
	vscroll.addAdjustmentListener(this);
	vscroll.setMaximum(visibleItemCount);
	vscroll.setVisibleAmount(visibleItemCount);
	vscroll.setBlockIncrement(visibleItemCount);
    }
    
    /**
     * Sets 'a' as horizontal Scrollbar.
     * The Browser becomes an AdjusmentListener of this scrollbar.
     */
    public void setHorizontalScrollbar(JScrollBar a) {
	hscroll = a;
	hscroll.addAdjustmentListener(this);
	int myWidth = getSize().width-HMARGIN*2;
	hscroll.setMaximum(myWidth);
	hscroll.setVisibleAmount(myWidth);
	hscroll.setBlockIncrement(20);
    }
    
    /**
     * Upgrades the graphical appearance in response to scroll.
     */
    public void adjustmentValueChanged(AdjustmentEvent evt){
	if (evt.getSource() == vscroll) {
	    topItem = evt.getValue();
	} else {
	    startx = evt.getValue();
	}
	repaint();
    }

    /**
     * Returns the parent node of a specified node.
     * If 'child' node has parent node, returns its parent.
     * Otherwise return null;
     * @param child the child node you want to get its parent.
     */
    public TreeNode getParent(TreeNode child){
	int n = items.indexOf(child);
	for(int i=n-1;i>=0;i--){
	    TreeNode node = (TreeNode)(items.elementAt(i));
	    if(node.level<child.level){
		return node;
	    }
	}
	return null;
    }

    /**
     * Returns node associated to specified object, or null if any.
     * @param obj the object related to node. 
     */
    public TreeNode getNode(Object obj){
	int imax = items.size();
	for(int i=0;i<imax;i++) {
	    if(obj.equals(((TreeNode)(items.elementAt(i))).getItem()))
		return (TreeNode)(items.elementAt(i));
	}return null;
    }


    public TreeNode getObjectNode(Object obj){
	int imax = items.size();
	TreeNode node;
	for(int i=0;i<imax;i++) {
	    node = (TreeNode)items.elementAt(i);
	    if (node.getObject() != null && node.getObject().equals(obj))
		return node;	    
	}
	return null;
    }
}
