package days.day05;

class Range {
    private final long start;
    private final long end;

    public Range(long start, long end) {
        this.start = start;
        this.end = end;
    }

    public boolean contains(long value) {
        return value >= start && value <= end;
    }

    public long getStart() { return start; }
    public long getEnd() { return end; }

    @Override
    public String toString() {
        return start + "-" + end;
    }
}
