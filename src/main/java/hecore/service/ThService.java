package hecore.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import hecore.entity.Th;
@Service
public class ThService {
	public List<Th> findThList(){
		List<Th> li=new ArrayList<Th>();
		Th t1=new Th(1L, "cse");
		Th t2=new Th(2L, "sre");
		li.add(t1);
		li.add(t2);
		return li;
	};

}
