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
 * $Id: NodeId.java,v 1.1 2014/03/28 17:33:31 xrf Exp $ 
 */


package com.spirit.util.tree;


/**
 * The interface to be implemented by nodes.
 * @author: crow@nolimits.ro
 * @see ro.nolimits.gui.tree.DrawTree
 */

public interface NodeId{

    /**
     * Notifies that a node has to be selected.
     *
     * @param browser the DrawTree sending the notification.
     */
    public void notifySelect(DrawTree browser, TreeNode node);
     
    /**
     * Notifies that a node has to be expanded.
     *
     * @param browser the DrawTree sending the notification.
     */
    public void notifyExpand(DrawTree browser, TreeNode node);
      
    /**
     * Notifies that a node has to be collapsed.
     *
     * @param browser the DrawTree sending the notification.
     */
    public void notifyCollapse(DrawTree browser, TreeNode node);
     
    /**
     * Notifies that a node has to be executed.
     *
     * @param browser the DrawTree sending the notification.
     */
    public void notifyExecute(DrawTree browser, TreeNode node);
}     
