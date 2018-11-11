package org.sid.formation.metier;

import java.util.List;
import java.util.Set;

import org.sid.formation.entities.Fonctions;
import org.sid.formation.entities.SousDirection;

public interface ISousDirection {

	public List<SousDirection> listSousDirection();
	public SousDirection GetSousDirectionById(long id);
}
