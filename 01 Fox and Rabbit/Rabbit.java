public class Rabbit extends Animal {

  boolean evading = false;
  int lastDirection = 8;

    public Rabbit(Model model, int row, int column) {
        super(model, row, column);
    }

    int decideMove() {
      lastDirection = makeMove();
      return lastDirection;
    }
    
    int makeMove() {
      for (int i = Model.MIN_DIRECTION; i <= Model.MAX_DIRECTION; i++) {
        if (look(i) == Model.FOX) {
          evading = true;
          if (canMove(Model.turn(i, -3))) return Model.turn(i, -3);
            if (canMove(Model.turn(i, 3))) return Model.turn(i, 3);
            if (canMove(Model.turn(i, 4))) return Model.turn(i, 4);
            if (canMove(Model.turn(i, -2))) return Model.turn(i, -2);
            if (canMove(Model.turn(i, 2))) return Model.turn(i, 2);
            if (canMove(Model.turn(i, -1))) return Model.turn(i, -1);
            if (canMove(Model.turn(i, 1))) return Model.turn(i, 1);
            return i;
        }
      }
      if (evading) {
        evading = false;
        if (canMove(lastDirection+1)) return lastDirection+1;
        if (canMove(lastDirection+-1)) return lastDirection-1;
        return random(Model.MIN_DIRECTION, Model.MAX_DIRECTION);
      }
      // if (availableCorners() < 3) {
      //   int direction = random(Model.MIN_DIRECTION, Model.MAX_DIRECTION);
      //   while (!canMove(direction)) {
      //     direction = random(Model.MIN_DIRECTION, Model.MAX_DIRECTION);
      //   }
      //   return direction;
      // }
      return Model.STAY;
    }

    public int availableCorners() {
      int cornersOpen = 0;
      for (int i = Model.NE; i <= Model.MAX_DIRECTION; i = i+2) {
        if (distance(i) != 1) {
          cornersOpen++;
        }
      }
      return cornersOpen;
    }
}
