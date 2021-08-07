/*
 * The MIT License
 *
 * Copyright 2012- Petteri Kivimäki
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.pkrete.jsip2.variables;

import com.pkrete.jsip2.exceptions.InvalidSIP2ResponseValueException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class generates ItemType objects based on the item code. 
 * 
 * This class implements the Singleton design pattern, which means that 
 * only one instance is created at run time.
 * 
 * @author Petteri Kivimäki
 */
public class ItemTypeFactory {
    /**
     * Reference to the singleton object.
     */
    private static ItemTypeFactory ref;
    /**
     * Constructs and initializes a new ItemTypeFactory object.
     */
    private ItemTypeFactory() {
    }
    
    /**
     * Returns a reference to the singleton object. The object is created when
     * this method is called for the first time.
     * @return reference to the singleton object
     */
    public static ItemTypeFactory getInstance() {
        if (ref == null) {
            ref = new ItemTypeFactory();
        }
        return ref;
    }

    /**
     * Returns the item type that matches the given code.
     * @param code item type code
     * @return item type that matches the given code
     * @throws InvalidSIP2ResponseValueException
     */
    public ItemType getItemType(String code) throws InvalidSIP2ResponseValueException {
        if (code.equals("AS")) {
            return ItemType.HOLD;
        } else if (code.equals("AT")) {
            return ItemType.OVERDUE;
        } else if (code.equals("AU")) {
            return ItemType.CHARGED;
        } else if (code.equals("AV")) {
            return ItemType.FINE;
        } else if (code.equals("BU")) {
            return ItemType.RECALL;
        } else if (code.equals("CD")) {
            return ItemType.UNAVAILABLE_HOLD;
        }else {
            throw new InvalidSIP2ResponseValueException("Invalid item type code! The given code \"" + code + "\" doesn't match with any item type!");
        }        
    }    
    
    /**
     * Returns a list that contains all the item types.
     * @return list of all the item types
     */
    public List<ItemType> getAllItemTypes() {
        List<ItemType> list = new ArrayList<ItemType>();
        list.add(ItemType.HOLD);
        list.add(ItemType.OVERDUE);
        list.add(ItemType.CHARGED);
        list.add(ItemType.FINE);
        list.add(ItemType.RECALL);
        list.add(ItemType.UNAVAILABLE_HOLD);
        return list;
    }
}
