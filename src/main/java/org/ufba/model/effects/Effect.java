package org.ufba.model.effects;

public enum Effect {
    // Efeitos de Dano
    DANO_SIMPLES,
    DANO_EXTREMO,
    WAVE, // Dano que aumenta a cada uso consecutivo
    ROUBO_DE_VIDA,

    // Efeitos de Cura
    CURA,

    // Efeitos de Status/Utility
    QUEBRA_ROBUSTEZ, // Efeito que desativa a robustez do inimigo
    NENHUM // Para cartas que só causam dano base
}
