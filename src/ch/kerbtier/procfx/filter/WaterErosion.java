package ch.kerbtier.procfx.filter;

import java.util.Arrays;
import java.util.Random;

import ch.kerbtier.procfx.core.DefaultMonoCanvasOperation;
import static ch.kerbtier.procfx.Coords.*;

public class WaterErosion extends DefaultMonoCanvasOperation {

  private int iterations = 20;
  private int water = 10;
  private int heat = 10;
  
  @Override
  public void calculate() {
    super.calculate();
    
    System.out.println("EROSION");
    
    
    Random random = new Random();
    int width = width();
    int height = height();

    mono = Arrays.copyOf(source.mono(), source.mono().length);

    int drops = width * height / 10;
    
    int waterTotal = drops * water;
    int heatTotal = drops * heat;

    for (int steps = 0; steps < iterations; steps++) {
      for (int drop = 0; drop < waterTotal; drop++) {
        waterErosion(random, width, height);
      }
      for (int drop = 0; drop < heatTotal; drop++) {
        thermalErosion(random, width, height);
      }
    }

  }

  private void thermalErosion(Random random, int width, int height) {
    int px = random.nextInt(width);
    int py = random.nextInt(height);
    int target = repeat(px, py, width, height);

    int lowestTarget = getLowestNeighbour(width, height, px, py, target);

    if (lowestTarget != target) {
      float diff = mono[target] - mono[lowestTarget];
      diff = (diff - 0.009f) / 2;
      if(diff > 0) {
        mono[target] -= diff;
        mono[lowestTarget] += diff;
      }
    }

  }

  private int getLowestNeighbour(int width, int height, int px, int py,
      int target) {
    int lowestTarget = target;
    float lowestH = mono[target];

    int ttarget = repeat(px + 1, py, width, height);
    float nh = mono[ttarget];
    if (nh < lowestH) {
      lowestH = nh;
      lowestTarget = ttarget;
    }

    ttarget = repeat(px - 1, py, width, height);
    nh = mono[ttarget];
    if (nh < lowestH) {
      lowestH = nh;
      lowestTarget = ttarget;
    }

    ttarget = repeat(px, py + 1, width, height);
    nh = mono[ttarget];
    if (nh < lowestH) {
      lowestH = nh;
      lowestTarget = ttarget;
    }

    ttarget = repeat(px, py - 1, width, height);
    nh = mono[ttarget];
    if (nh < lowestH) {
      lowestH = nh;
      lowestTarget = ttarget;
    }
    return lowestTarget;
  }

  private void waterErosion(Random random, int width, int height) {
    int px = random.nextInt(width);
    int py = random.nextInt(height);

    boolean deposit = false;

    while (!deposit) {
      int target = repeat(px, py, width, height);

      int lowestTarget = getLowestNeighbour(width, height, px, py, target);

      if (lowestTarget != target) {
        // there is lower terrain, flow there
        mono[lowestTarget] -= 0.0001f;

        px = lowestTarget % width;
        py = lowestTarget / width;
      } else {
        deposit = true;
        mono[lowestTarget] += 0.001f;
      }
    }
  }

  // unused
  private void rainFlowSedimentDeposit() {
    super.calculate();
    Random random = new Random();
    int width = width();
    int height = height();

    mono = Arrays.copyOf(source.mono(), source.mono().length);

    float[] water = new float[width() * height()];

    for (int cnt = 0; cnt < 3; cnt++) {
      for (int rain = 0; rain < water.length; rain++) {
        water[rain] = water[rain] + 0.1f;
      }

      for (int physics = 0; physics < 10000000; physics++) {
        int px = random.nextInt(width);
        int py = random.nextInt(height);

        float h = mono[repeat(px, py, width, height)]
            + water[repeat(px, py, width, height)];

        int targetX = px;
        int targetY = py;
        float lowestH = h;

        float nh = mono[repeat(px + 1, py, width, height)]
            + water[repeat(px + 1, py, width, height)];
        if (nh < lowestH) {
          lowestH = nh;
          targetX = px + 1;
          targetY = py;
        }

        nh = mono[repeat(px - 1, py, width, height)]
            + water[repeat(px - 1, py, width, height)];
        if (nh < lowestH) {
          lowestH = nh;
          targetX = px - 1;
          targetY = py;
        }

        nh = mono[repeat(px, py + 1, width, height)]
            + water[repeat(px, py + 1, width, height)];
        if (nh < lowestH) {
          lowestH = nh;
          targetX = px;
          targetY = py + 1;
        }

        nh = mono[repeat(px, py - 1, width, height)]
            + water[repeat(px, py - 1, width, height)];
        if (nh < lowestH) {
          lowestH = nh;
          targetX = px;
          targetY = py - 1;
        }

        if (targetX != px || targetY != py) { // there is lower terrain, flow
                                              // there

          float amount = (h - lowestH) / 2;

          if (water[repeat(px, py, width, height)] < amount) {
            amount = water[repeat(px, py, width, height)];
          }

          water[repeat(px, py, width, height)] -= amount;
          water[repeat(targetX, targetY, width, height)] += amount;
          // mono[repeat(targetX, targetY, width, height)] -= amount / 10f;
        }
      }

      // deposit

      for (int k = 0; k < water.length; k++) {
        mono[k] += water[k] / 10f;
      }

      Arrays.fill(water, 0);

    }

    // mono = water;
  }
}
