package com.spxvol.www.model;

import java.util.List;

import com.spxvol.www.datastore.OptionQuote;
import com.spxvol.www.datastore.Underlying;

public class StandardQuote {

	private Underlying underlying;

	private List<OptionQuote> options;

	public Underlying getUnderlying() {
		return underlying;
	}

	public void setUnderlying(Underlying underlying) {
		this.underlying = underlying;
	}

	public List<OptionQuote> getOptions() {
		return options;
	}

	public void setOptions(List<OptionQuote> options) {
		this.options = options;
	}

}
