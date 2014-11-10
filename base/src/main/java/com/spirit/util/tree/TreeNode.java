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
 * $Id: TreeNode.java,v 1.1 2014/03/28 17:33:31 xrf Exp $ 
 */


package com.spirit.util.tree;

import java.awt.Color;
import java.awt.Image;
import java.awt.PopupMenu;

import com.spirit.client.model.SpiritResourceManager;

/**
 * The representation of a node of a DrawTree.
 *
 * A TreeNode is used by the DrawTree to store informations
 * related to a node.
 *
 * It is also given as parameter in the notifications DrawTree
 * send to handlers.
 * @author: crow@nolimits.ro
 * @see ro.nolimits.gui.tree.DrawTree
 * @see ro.nolimits.gui.tree.NodeId
 */
  
public class TreeNode{
    
    /**
     * Contains path to default icon close of non leaf node.
     */
    public static final String close_icon = "images/icons/database.png";
    
    /**
     * Contains path to default icon open of non leaf node.
     */
    public static final String open_icon = "images/icons/database.png";
    
    
    /**
     * Indicate if a node have child.	
     */
    public static final int NOCHILD = -1;
    
    /**
     * Indicate if a node is leaf node.
     */
    public static final int LEAF    = 1;
    
    /**
     * Indicate if a node is non leaf node.
     */
    public static final int NODE    = 2;
    
    /**
     * Indicate the leaf node color.
     */
    public static final Color LEAF_COLOR = new Color(0,0,128); 
    
    Object    item;
    Object    asObj;
    String    label;
    Image     icon_open,icon_close;
    NodeId    id = null;
    int       level;
    int       children = NOCHILD;
    int       childinv = NOCHILD;
    boolean   selected = false;
    int       isNode   = NODE;
    boolean   activate = false;
    Color     leaf_color = LEAF_COLOR;
    PopupMenu pm = null;
    
    /**
     * Gets the image from specificed path.
     *
     * @param name the path to image.
     */
    private final Image getImage(String name){
	//return Toolkit.getDefaultToolkit().getImage(name);
    	return SpiritResourceManager.getImageIcon(name).getImage();
    }
        
    /**
     *	Builds a new node instance
     *
     * @param item the abstract object this node refers to. may be null.
     * @param label the the label that should be dispayed for this item
     * @param id the id for this node
     * @param isNode sets type of the node
     * @param level sets level for this node
     */
    TreeNode(Object item, String label, NodeId id, int isNode, int level, Object asObj){
	this.id         = id;
	this.label      = label;
	this.item       = item;	
	this.level      = level;
	this.isNode     = isNode;
	this.asObj      = asObj;
	if(isNode==NODE){
	    this.icon_open  = getImage(open_icon);
	    this.icon_close = getImage(close_icon);
	}
    }

    /**
     *	Builds a new node instance
     *
     * @param item the abstract object this node refers to. may be null.
     * @param label the the label that should be dispayed for this item
     * @param id the id for this node
     * @param icon_open sets first icon for this node
     * @param icon_close sets second icon for this node
     * @param isNode sets type of the node
     * @param level sets level for this node
     */
    public TreeNode(Object item, String label, NodeId id, String icon_open, String icon_close, int isNode, int level){
	this.id         = id;
	this.label      = label;    
	this.icon_open  = getImage(icon_open);
	this.icon_close = getImage(icon_close);	
	this.isNode     = isNode;
	this.level      = level;
	this.item       = item;
    }

    /**
     *	Builds a new node instance
     *
     * @param item the abstract object this node refers to. may be null.
     * @param label the the label that should be dispayed for this item
     * @param id the id for this node
     * @param icon_open sets first icon for this node
     * @param icon_close sets second icon for this node
     * @param isNode sets type of the node
     * @param level sets level for this node
     */
    TreeNode(Object item, String label, NodeId id, 
	     String icon_open, String icon_close, int isNode, 
	     int level, Object asObj){
	this.id         = id;
	this.label      = "  " + label;    
	this.icon_open  = getImage(icon_open);
	this.icon_close = getImage(icon_close);	
	this.isNode     = isNode;
	this.level      = level;
	this.item       = item;
	this.asObj      = asObj;
    }



    public Object getObject(){
	return asObj;
    }

    public void setObject(Object asObj){
	this.asObj = asObj;
    }


    /**
     * Gets the item.
     */
    public Object getItem(){
	return item;
    }

    /**
     * Gets the label.
     *
     * @see #setLabel
     */
    public String getLabel(){
	return label;
    }
    
    /**
     * Gets first icon.
     *
     * @see #setOpenIcon
     */
    public Image getOpenIcon(){
	return icon_open;
    }
    
    /**
     * Gets second icon.
     *
     * @see #setCloseIcon
     */
    public Image getCloseIcon(){
	return icon_close;
    }

    /**
     * Gets the Id.
     */
    public NodeId getId(){
	return id;
    }

    /**
     * Gets the children.
     *
     * @see #setChildren
     */
    public int getChildren(){
 	return children;
    }
    
    /**
     * Gets the leaf color.
     *
     * @see #setLeafColor
     */
    public Color getLeafColor(){
	return leaf_color;
    }

    /**
     * Gets popupmenu for this node.	
     *
     * @see #setPopupMenu
     */
    public PopupMenu getPopupMenu(){
	return pm;
    }
    /**
     * Checks if the Node is leaf node.
     */
    public int isNode(){
	return isNode;
    }

    /**
     * Checks if the Node is selected.
     */
    public boolean isSelected(){
	return selected;
    }

    /**
     * Sets first icon.
     *
     * @see #getOpenIcon
     */
    public void setOpenIcon(Image i){
	icon_open = i;
    }

    /**
     * Sets second icon.
     *
     * @see #getCloseIcon
     */
    public void setCloseIcon(Image i){
	icon_close = i;
    }
    
    /**
     * Sets the label.
     *
     * @see #getLabel
     */
    public void setLabel(String l){
	label = l;
    }

    /**
     * Sets the children.
     *
     * @see #getChildren
     */
    public void setChildren(int children){
	this.children = children;
    }
    
    /**
     * Sets the leaf color
     *
     * @see #getLeafColor
     */
    public void setLeafColor(Color c){
	this.leaf_color = c;
    }
     
    /**
     * Sets the popup menu for this node
     *
     * @see #getPopupMenu
     */
    public void setPopupMenu(PopupMenu pm){
	this.pm = pm;
    }
}
