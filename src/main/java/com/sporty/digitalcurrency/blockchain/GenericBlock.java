package com.sporty.digitalcurrency.blockchain;

public class GenericBlock implements Block {

    final private long hash;
    final long previousHash;
    final Block previousBlock;
    Object data;

    public GenericBlock(Object data, Block previousBlock) {
        this.data = data;
        this.previousHash = previousBlock != null ? previousBlock.computeHash() : -1;
        this.previousBlock = previousBlock;
        this.hash = computeHash();
    }

    public long computeHash() {

        return 0;
    }

}
