package com.ufes.blog_aulas.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SubjectDifficulyConverter implements AttributeConverter<SubjectDifficulty, String>
{
    @Override
    public String convertToDatabaseColumn(final SubjectDifficulty attribute)
    {
        if (attribute == null)
        {
            return null;
        }

        return attribute.sigla();
    }

    @Override
    public SubjectDifficulty convertToEntityAttribute(final String dbData)
    {
        if (dbData == null || dbData.length() != 2)
        {
            // Talvez você queira lançar uma excessão aqui...
            return null;
        }

        return SubjectDifficulty.fromSigla(dbData);
    }
}
