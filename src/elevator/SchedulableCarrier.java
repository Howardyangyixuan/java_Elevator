package elevator;

public interface SchedulableCarrier {
    final double moveTime = 0.5, callTime = 1.0;
    abstract boolean moveUP();
    abstract boolean moveDOWN();
    abstract boolean callOpenAndClose();
}
