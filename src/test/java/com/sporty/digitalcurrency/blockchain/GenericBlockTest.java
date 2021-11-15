package com.sporty.digitalcurrency.blockchain;

import com.sun.tools.javac.util.Pair;
import lombok.Getter;
import org.junit.Assert;
import org.junit.Test;

public class GenericBlockTest {

    @Test
    public void testBlockStructure() {
        GenericBlock root = new GenericBlock("I am root", null);
        GenericBlock b1 = new GenericBlock("Im a child!", root);
        Assert.assertEquals("B1 hash matches root's hash", root.computeHash() , b1.previousHash);

        GenericBlock b2 = new GenericBlock("Im a brother!", root);
        byte[] byteContent = "I contain bytes!".getBytes();
        GenericBlock b3 = new GenericBlock("I contain bytes!".getBytes(), b1);
        GenericBlock b4 = new GenericBlock(12354, root);
        GenericBlock b5 = new GenericBlock(new SomeClass("Hi there!"), root);
        GenericBlock b6 = new GenericBlock("child of b4", b4);

        Assert.assertTrue("hashes " + b1.previousBlock + " should match", testNode(b1));
        Assert.assertTrue("hashes " + b2.previousBlock + " should match", testNode(b2));
        Assert.assertTrue("hashes " + b3.previousBlock + " should match", testNode(b3));
        Assert.assertTrue("hashes " + b4.previousBlock + " should match", testNode(b4));
        Assert.assertTrue("hashes " + b5.previousBlock + " should match", testNode(b1));
        Assert.assertTrue("hashes " + b6.previousBlock + " should match", testNode(b6));


//        Tampering
        b4.data = 123;
        Assert.assertFalse("Tampered has been detected", testNode(b4));

    }

    private boolean testNode(GenericBlock block) {
        return block.previousBlock.computeHash() == block.previousHash;
    }

    class SomeClass {
        @Getter
        private String s;
        private long num = 1234567;
        SomeClass(String s) {
            this.s = s;
        }
    }
}
