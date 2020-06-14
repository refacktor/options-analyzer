package com.spxvol.www.datasources;

import com.spxvol.www.model.StandardQuote;

public interface OptionsDataSource {

	StandardQuote getQuote(String stock);

}