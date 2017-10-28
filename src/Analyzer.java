
public abstract class Analyzer implements Filter{
	public abstract void saveRecord(Record record);
	public abstract void close();
}
