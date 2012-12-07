package net.mystia.MystiaUtils;

import java.util.List;

import org.bukkit.entity.Player;

import com.ensifera.animosity.craftirc.EndPoint;
import com.ensifera.animosity.craftirc.RelayedMessage;


public class JoinIRCEndpoint implements EndPoint
{
	
	@Override
	public boolean adminMessageIn(RelayedMessage arg0)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Type getType()
	{
		return EndPoint.Type.MINECRAFT;
	}

	@Override
	public List<String> listDisplayUsers()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> listUsers()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void messageIn(RelayedMessage arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean userMessageIn(String arg0, RelayedMessage arg1)
	{
		// TODO Auto-generated method stub
		return false;
	}
	
	
	public void messageOut(Player player, String type) {
		RelayedMessage msg = MystiaUtilsMain.craftIRC.newMsg(this, null, type);
        msg.setField("sender", player.getDisplayName());
        msg.setField("world", player.getWorld().getName());
        msg.setField("realSender", player.getName());
		msg.post();
	}
	

	
}
