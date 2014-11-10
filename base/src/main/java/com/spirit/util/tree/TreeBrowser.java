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
 * $Id: TreeBrowser.java,v 1.1 2014/03/28 17:33:31 xrf Exp $ 
 */

package com.spirit.util.tree;

import java.util.Vector;

/**
 * The TreeBrowser class.	
 * @author: crow@nolimits.ro
 */
public class TreeBrowser extends DrawTree implements NodeId{
    //This vector is private, and contains the BrowserListeners
    private Vector BrowserListeners = new Vector();
    TreeNode lastn;
    
    /**
     * This function is private, its not be accessed an other class.
     */
    private void fireSelect(TreeNode node){
	Vector t = (Vector)BrowserListeners.clone();
	if(t.size()==0)return;
	BrowserEvent event = new BrowserEvent(node);
	for(int i=0;i<t.size();i++){
	    BrowserListener BL=(BrowserListener)t.elementAt(i);BL.Select(event);
	}
    }

    /**
     * This function is private, its not be accessed an other class.
     */
    private void fireDeselect(TreeNode node){
	Vector t = (Vector)BrowserListeners.clone();
	if(t.size()==0)return;
	BrowserEvent event = new BrowserEvent(node);
	for(int i=0;i<t.size();i++){
	    BrowserListener BL=(BrowserListener)t.elementAt(i);BL.Deselect(event);
	}
    }

    /**
     * This function is private, its not be accessed an other class.
     */
    private void fireActivate(TreeNode node){
	Vector t = (Vector)BrowserListeners.clone();
	if(t.size()==0)return;
	BrowserEvent event = new BrowserEvent(node);
	for(int i=0;i<t.size();i++){
	    BrowserListener BL=(BrowserListener)t.elementAt(i);BL.Activate(event);
	}
    }

    /**
     * This function is private, its not be accessed an other class.
     */
    private void fireDeactivate(TreeNode node){
	Vector t = (Vector)BrowserListeners.clone();
	if(t.size()==0)return;
	BrowserEvent event = new BrowserEvent(node);
	for(int i=0;i<t.size();i++){
	    BrowserListener BL=(BrowserListener)t.elementAt(i);BL.Deactivate(event);
	}
    }

    /**
     * Add BrowserListener to the Browser.
     *
     * @see ro.nolimits.gui.tree.BrowserListener
     */
    public synchronized void addBrowserListener(BrowserListener l){
	if(BrowserListeners.contains(l))return;
	BrowserListeners.addElement(l);
    }
    
    /**
     * Remove BrowserListener from this browser.
     *
     * @see ro.nolimits.gui.tree.BrowserListener 
     */
    public synchronized void removeBrowserListener(BrowserListener l){
	BrowserListeners.removeElement(l);
    }

    class Expander extends Thread{
	DrawTree browser;
	TreeNode node;
	
	Expander(DrawTree br,TreeNode nd){
	    browser = br;
	    node = nd;
	}
	
	public void run(){
	    notifyExpander(browser,node);
	}
    }

    /**
     * Builds a new TreeBrowser instance.
     *
     * @param root the root node for this hierarchy
     * @param label the label that should be displayed for this item
     * @param isNode specifies if the node is a leaf node
     */
    public TreeBrowser(Object root, String label, int isNode){
	initialize(root, label, this, isNode);
    }

    
    public TreeBrowser(String label, String open , String close,  int isNode){
	initialize(this, label, this,  open, close, isNode);
    }


    /**
     * Insert a new node to Tree.
     *
     * @param father the father node
     * @param label the label that should be dispalyed for this item
     * @param isNode specifies if da node is a leaf node
     */
    public Object insertNode(Object father,String label, int isNode, Object item){
    	TreeNode fatherNode;
	Object son = new Object();
	if(father == null)System.out.println("Error null father !!!");
	fatherNode = getNode(father);
	insert(fatherNode,son,this,label,isNode, item);
	return son;
    }

    /**
     * Insert a new node to Tree.
     *
     * @param father the father node
     * @param label the label that should be dispalyed for this item
     * @param icon_open the first icon for this node
     * @param icon_close the second icon for this node
     * @param isNode specifies if da node is a leaf node
     */

    public Object insertNode(Object father,String label, String icon_open, String icon_close, int isNode){
    	TreeNode fatherNode;
	Object son = new Object();
	if(father == null)System.out.println("Error null father !!!");
	fatherNode = getNode(father);
	insert(fatherNode,son,this,label,icon_open,icon_close,isNode);
	return son;
    }

    /**
     * Insert a new node to Tree.
     *
     * @param father the father node
     * @param label the label that should be dispalyed for this item
     * @param icon_open the first icon for this node
     * @param icon_close the second icon for this node
     * @param isNode specifies if da node is a leaf node
     * @param item the object associated with this node
     */

    public Object insertNode(Object father,String label, 
			     String icon_open, String icon_close, 
			     int isNode, Object item){
    	TreeNode fatherNode;
	Object son = new Object();
	if(father == null)System.out.println("Error null father !!!");
	fatherNode = getNode(father);
	insert(fatherNode,son,this,label,icon_open,icon_close,isNode, item);
	return son;
    }

    /**
     * Handles Select notifications.
     *
     * we simply select the given node and repaint the browser
     */
    public void notifySelect(DrawTree browser, TreeNode node){
	browser.unselect(lastn);
	if(lastn!=null)fireDeselect(lastn);
	browser.select(node);
	fireSelect(node);
	browser.repaint();
	lastn = node;	
    }

    public void notifyExpand(DrawTree browser, TreeNode node){
	(new Expander(browser,node)).start();
    }

    /**
     * Handles Collapse notification.
     *
     * we simple collapse the given node and repaint teh browser
     */
    public void notifyCollapse(DrawTree browser, TreeNode node){
	browser.collapse(node);
	if(!node.equals(lastn)){
	    browser.unselect(lastn);
	    lastn = null;
	}browser.repaint();
    }
    
    /**
     * Handles Execute notification.
     *
     * if node is activated, sets node to deactive and otherway
     */
    public void notifyExecute(DrawTree browser, TreeNode node){
	if(!node.activate){
	    fireActivate(node);node.activate=true;
	}
	else{
	    fireDeactivate(node);node.activate=false;
	}browser.repaint();
    }

    /**
     * Handles Expand notification.
     *
     * if the node is a directory, sets the invisible childrens(if found) 
     * to visible childrens and repaint the browser 
     */
    public void notifyExpander(DrawTree browser, TreeNode node){
	Object item = node.getItem();
	if(item==null)return;
	if((node.children == TreeNode.NOCHILD)&&(node.childinv !=TreeNode.NOCHILD)){
	    node.children = node.childinv;
	    node.childinv = TreeNode.NOCHILD;
	    for(int j = items.indexOf(item)+1;j<items.size();j++){
		TreeNode child = (TreeNode)items.elementAt(j);
		TreeNode parent = getParent(child);
		if(child.level>node.level&&parent.children!=TreeNode.NOCHILD){
		    if(child.selected){
			unselect(child);
		    }
		}else{
		    browser.changeSize();break;
		}
	    }browser.repaint();
	}
    }
}
