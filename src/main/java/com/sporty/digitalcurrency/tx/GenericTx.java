package com.sporty.digitalcurrency.tx;

import java.util.ArrayList;
import java.util.List;

public class GenericTx {

    private List<Long> inputs;

    private List<Long> outputs;

    private List<String> signatures;

    private List<Long> requireAddresses;


    public void addInput(Long input) {
        if (inputs == null) {
            inputs = new ArrayList<>();
        }
        inputs.add(input);
    }

    public void addOutput(Long output) {
        if (outputs == null) {
            outputs = new ArrayList<>();
        }
        outputs.add(output);
    }

    public void addSignature(String sig) {
        if (signatures == null) {
            signatures = new ArrayList<>();
        }
        signatures.add(sig);
    }

    public void addRequireAddr(Long publicKey) {
        if (requireAddresses == null) {
            requireAddresses = new ArrayList<>();
        }
        requireAddresses.add(publicKey);
    }

    public boolean isValid() {
        return false;
    }
}
