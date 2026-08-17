package com.ufes.blog_aulas.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum SubjectDifficulty
{
    MUITO_FACIL("Muito Fácil"),
    FACIL("Fácil"),
    NORMAL("Normal"),
    DIFICIL("Difícil"),
    MUITO_DIFICIL("Muito Difícil");

    private final String difficulty;

    SubjectDifficulty(String difficulty)
    {
        this.difficulty = difficulty;
    }

    /**
     * Converte para Enum, a partir da sigla da federação para o enum UF.
     */
    @JsonCreator
    public static SubjectDifficulty fromSigla(final String difficulty)
    {
        for (final SubjectDifficulty subjectDifficulty : SubjectDifficulty.values())
        {
            if (subjectDifficulty.name().equalsIgnoreCase(difficulty))
            {
                return subjectDifficulty;
            }
        }

        return null;
    }

    public String sigla()
    {
        return this.name();
    }
}
