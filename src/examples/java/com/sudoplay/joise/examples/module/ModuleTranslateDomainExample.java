package com.sudoplay.joise.examples.module;

import com.sudoplay.joise.examples.AbstractSplitExample;
import com.sudoplay.joise.examples.SplitCanvas;
import com.sudoplay.joise.module.*;

/**
 * Created by codetaylor on 1/21/2017.
 */
public class ModuleTranslateDomainExample extends
    AbstractSplitExample {

  static {
    EXAMPLE_CLASS = ModuleTranslateDomainExample.class;
  }

  @Override
  protected String getWindowTitle() {
    return "ModuleTranslateDomain Example";
  }

  @Override
  protected void run(SplitCanvas canvas) {

    ModuleFractal source = new ModuleFractal();
    source.setAllSourceBasisTypes(ModuleBasisFunction.BasisType.SIMPLEX);
    source.setAllSourceInterpolationTypes(ModuleBasisFunction.InterpolationType.CUBIC);
    source.setNumOctaves(5);
    source.setFrequency(2.34);
    source.setType(ModuleFractal.FractalType.RIDGEMULTI);
    source.setSeed(42);

    /**
     * We auto-correct into the range [0, 1], performing 10,000 samples to calculate the auto-correct values.
     */
    ModuleAutoCorrect moduleAutoCorrect = new ModuleAutoCorrect(0, 1);
    moduleAutoCorrect.setSource(source);
    moduleAutoCorrect.setSamples(10000);
    moduleAutoCorrect.calculate();

    ModuleTranslateDomain moduleTranslateDomain = new ModuleTranslateDomain();
    moduleTranslateDomain.setSource(moduleAutoCorrect);
    moduleTranslateDomain.setAxisXSource(-0.5);

    canvas.updateImage(
        "source", moduleAutoCorrect,
        "translateDomain (x=-0.5)", moduleTranslateDomain
    );
  }

}
