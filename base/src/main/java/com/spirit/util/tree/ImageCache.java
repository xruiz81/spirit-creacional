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
 * $Id: ImageCache.java,v 1.1 2014/03/28 17:33:31 xrf Exp $ 
 */

package com.spirit.util.tree;


import java.awt.Component;
import java.awt.Image;
import java.util.Hashtable;

/**
 * A Basic Image Cache class.
 * ImageCache.java
 * @author: crow@nolimits.ro
 */
public class ImageCache {
    private static Hashtable images = new Hashtable();

    /**
     * Gets an Image of the requested size.
     *
     * Checks if an Image already exists in the cache for the current Thread and
     * if this image is large enough. Else, creates a new Image and store it in
     * the cache.
     */
    static public Image getImage(Component c, int w, int h) {

	Image img = (Image)images.get(Thread.currentThread());

	if((img == null) || (img.getWidth(c) < w)||(img.getHeight(c) < h)) {
	    img = c.createImage(w, h);
	    images.put(Thread.currentThread(), img);
	}return img;

    }
}
