package com.example.calculadora;

import android.support.annotation.NonNull;

/**
 * Created by William_ST on 28/02/19.
 */

public interface Calculator {

    String addSymbol(@NonNull String to, @NonNull String symbol);

    String removeSymbol(@NonNull String from);

    String calculate(@NonNull String from);

}
