package brainacad.org.Dao.DB_Dao.LanguageDAO;

import brainacad.org.Dao.CRUD_Interface;
import brainacad.org.Models.Language.Language;

public interface LanguageDAO extends CRUD_Interface<Language>
{
    String addCode(Language language);
    int deleteCode(Language language);
}
