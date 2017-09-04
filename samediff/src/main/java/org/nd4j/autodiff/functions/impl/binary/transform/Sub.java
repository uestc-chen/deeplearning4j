package org.nd4j.autodiff.functions.impl.binary.transform;

import org.nd4j.autodiff.ArrayField;
import org.nd4j.autodiff.functions.AbstractBinaryFunction;
import org.nd4j.autodiff.functions.DifferentialFunction;
import org.nd4j.autodiff.samediff.SameDiff;
import org.nd4j.linalg.api.ops.impl.transforms.arithmetic.SubOp;

import java.util.ArrayList;
import java.util.List;

public class Sub extends AbstractBinaryFunction {
    public Sub(SameDiff sameDiff, DifferentialFunction i_v1, DifferentialFunction i_v2) {
        super(sameDiff, i_v1, i_v2);
    }

    @Override
    public ArrayField doGetValue() {
        return larg().getValue(true).sub(rarg().getValue(true));
    }


    @Override
    public List<DifferentialFunction> diff(List<DifferentialFunction> i_v) {
        DifferentialFunction gradWrtX = i_v.get(0);
        DifferentialFunction gradWrtY = f().neg(i_v.get(0));
        List<DifferentialFunction> ret = new ArrayList<>();
        ret.add(gradWrtX);
        ret.add(gradWrtY);
        larg().setGradient(gradWrtX);
        rarg().setGradient(gradWrtY);
        return ret;
    }



    @Override
    public String functionName() {
        return new SubOp().name();
    }
}
