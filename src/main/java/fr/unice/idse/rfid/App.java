package fr.unice.idse.rfid;

import java.util.List;

import javax.smartcardio.*;

@SuppressWarnings("restriction")
public class App 
{	
	private static Card card = null;
	private static CardChannel channel = null;
	
    public static void main( String[] args ) throws CardException
    {
    	if(openConnection()) {
    		System.out.println("Connection open!");
	    	ATR atr = card.getATR();
	        byte[] baAtr = atr.getBytes();
	        System.out.print("ATR = 0x");
            for(int i = 0; i < baAtr.length; i++ ){
                System.out.printf("%02X ",baAtr[i]);
            }
            System.out.println();
            byte[] cmdApduGetCardUid = new byte[]{(byte)0xFF, (byte)0xCA, (byte)0x00, (byte)0x00, (byte)0x00};
            ResponseAPDU respApdu = channel.transmit(new CommandAPDU(cmdApduGetCardUid));

			if(respApdu.getSW1() == 0x90 && respApdu.getSW2() == 0x00){
				byte[] baCardUid = respApdu.getData();
				
				System.out.print("Card UID = 0x");
				for(int i = 0; i < baCardUid.length; i++ ){
				   System.out.printf("%02X ", baCardUid [i]);
				}
			}
	        disconnect();
    	}
    }
	
	public static boolean openConnection() {
		TerminalFactory factory = TerminalFactory.getDefault();
		CardTerminals cardterminals = factory.terminals();
		card = null;
		try {
			List<CardTerminal> terminals = cardterminals.list();
			System.out.println("Terminals: " + terminals);
			CardTerminal terminal = cardterminals.getTerminal(terminals.get(0).getName());
			terminal.waitForCardPresent(20000);
			if(terminal.isCardPresent()) {
				System.out.println("Card detected!");
				card = terminal.connect("*");
				System.out.println("Card: " + card);
				channel = card.getBasicChannel();
				System.out.println("Channel: " + channel);
				return true;
			} else {
				System.out.println("No card detected!");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void disconnect() {
		try {
			card.disconnect(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
