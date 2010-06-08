package Machine;

/*-----------------------------------------------------------------------------
  Основная память.
-----------------------------------------------------------------------------*/
public class EMemory implements IRegister, IMemory
{
	public EMemory(ERegisterFactory factory)
	{
		this.adress_register = factory.AdressRegister();
		this.memory_width = 16;
		this.memory_length = (int) StrictMath.pow(2, factory.AdressRegister().Width());
		memory = new ERegister[memory_length];
		for (int i = 0; i < memory_length; i++)
		{
			memory[i] = new ERegister();
		}
	}
	
	public EMemory(ERegister adress_register, int length, int width)
	{
		this.adress_register = adress_register;
		this.memory_width = width;
		this.memory_length = length;
		memory = new ERegister[memory_length];
		for (int i = 0; i < memory_length; i++)
		{
			memory[i] = new ERegister();
		}
	}
	
	public int SendData()
	{
		return memory[MakeAdress()].SendData();
	}
	
	public void GetData(int bits)
	{
		memory[MakeAdress()].GetData(bits);
	}
	
	public int Width()
	{
		return memory_width;
	}
	
	private int MakeAdress()
	{
		return adress_register.SendData();
	}
	
	public int[] GetMemory()
	{
		int[] mem = new int[memory_length];
		for (int i = 0; i < memory_length; i++)
		{
			mem[i] = memory[i].SendData();
		}
		return mem;
	}
	
	public void SetValue(int bits, int adr)
	{
		memory[adr].GetData(bits);
	}
	
	private int			memory_length;   // Длина памяти
	private int			memory_width;    // Разрядность памяти
	private ERegister[]	memory;          // Память
	private ERegister	adress_register; // Регистр адреса
}