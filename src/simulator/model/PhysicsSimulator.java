package simulator.model;

import java.util.LinkedList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class PhysicsSimulator {

	
	private GravityLaws _gravityLaws;
	private List<Body> _bodies;
	private double _dt;
	private double _time; 
	private JSONObject stateJSON;

	public PhysicsSimulator(GravityLaws gravityLaws, Double _dtime){
		_gravityLaws = gravityLaws;
		_bodies = new LinkedList<Body>();
		if(_gravityLaws == null)
			throw new IllegalArgumentException();
		if(_dtime.isNaN())
			throw new IllegalArgumentException(); 
		else
			_dt = _dtime;
		_time = 0.0;
		stateJSON = new JSONObject();
	}

	public void addBody(Body b) throws IllegalArgumentException {
		for (Body body : _bodies) {
			if(body.getId().equals(b.getId()))
				throw new IllegalArgumentException("Cuerpo con ID duplicado");
		}
			_bodies.add(b);
	}
	
	public void advance() {
		_gravityLaws.apply(_bodies);
		for (Body body : _bodies) {
			body.move(_dt);
			body.toString();
		}
		_time+=_dt;
	}
	/*
	public String toString() {
		JSONObject info = new JSONObject(); 
		JSONArray bodies = new JSONArray(); 
		
		info.put("time", _time); 
		
		for (Body bodie : _bodies) {
			bodies.put(new JSONObject(bodie.toString())); 
		}
		
		info.put("bodies", bodies);
		
		return info.toString();
	}
	*/
	
	
	
	public String toString() {
		
		
		StringBuilder state = new StringBuilder();
		//state.append("{\r\n" + 
			//	"\"states\": [\r\n"); 
		state.append("{ \"time\":  ");
		state.append(_time);
		state.append(" ,  \"bodies\":  [ ");
	
		
		for (Body body : _bodies) {
			
			state.append(body.toString());
			state.append(" , ");
			
		}
		
		state.append(" ] } ,\n");
		
		
		return state.toString();
		
	}
	

}
