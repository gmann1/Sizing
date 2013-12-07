package voctec;

public interface Singleton
{
	public static class DuplicateInstantiationException extends Exception
	{
		private static final long serialVersionUID = 1L;
		
		public DuplicateInstantiationException()
		{
			this("attempt made to instantiate Singleton class more than once");
		}
		
		public DuplicateInstantiationException(String message)
		{
			super(message);
		}
		
		public DuplicateInstantiationException(String message, Throwable throwable)
		{
			super(message, throwable);
		}
	}
}
