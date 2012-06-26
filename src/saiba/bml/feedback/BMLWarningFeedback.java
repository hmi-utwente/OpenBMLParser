package saiba.bml.feedback;

import hmi.xml.CharDataConversion;
import hmi.xml.XMLFormatting;
import hmi.xml.XMLStructureAdapter;
import hmi.xml.XMLTokenizer;

import java.io.IOException;
import java.util.HashMap;

/**
 * Warning feedback
 * @author Herwin
 * 
 */
public class BMLWarningFeedback extends XMLStructureAdapter implements BMLFeedback
{
    public static final String PARSING_FAILURE = "PARSING_FAILURE";
    public static final String NO_SUCH_TARGET = "NO_SUCH_TARGET";
    public static final String IMPOSSIBLE_TO_SCHEDULE = "IMPOSSIBLE_TO_SCHEDULE";
    public static final String BEHAVIOR_TYPE_NOT_SUPPORTED = "BEHAVIOR_TYPE_NOT_SUPPORTED";
    public static final String CUSTOM_BEHAVIOUR_NOT_SUPPORTED = "CUSTOM_BEHAVIOUR_NOT_SUPPORTED";
    public static final String CUSTOM_ATTRIBUTE_NOT_SUPPORTED = "CUSTOM_ATTRIBUTE_NOT_SUPPORTED";
    public static final String CANNOT_CREATE_BEHAVIOR = "CANNOT_CREATE_BEHAVIOR";

    private String characterId;
    private String type;
    private String id;
    private String description;

    public BMLWarningFeedback()
    {
        
    }
    
    public BMLWarningFeedback(String id, String type, String description)
    {
        this.id = id;
        this.type = type;
        this.description = description;
    }
    
    public String getCharacterId()
    {
        return characterId;
    }

    public String getType()
    {
        return type;
    }

    public String getId()
    {
        return id;
    }

    public String getDescription()
    {
        return description;
    }

    @Override
    public void decodeContent(XMLTokenizer tokenizer) throws IOException
    {
        if (tokenizer.atCharData()) description = CharDataConversion.decode(tokenizer.takeTrimmedCharData());
    }

    @Override
    public void decodeAttributes(HashMap<String, String> attrMap, XMLTokenizer tokenizer)
    {
        characterId = getOptionalAttribute("characterId", attrMap);
        id = getRequiredAttribute("id", attrMap, tokenizer);
        type = getRequiredAttribute("type", attrMap, tokenizer);
    }

    @Override
    public StringBuilder appendAttributes(StringBuilder buf)
    {
        if(characterId!=null)
        {
            appendAttribute(buf, "characterId", characterId);
        }
        appendAttribute(buf, "id", id);
        appendAttribute(buf, "type", type);
        return buf;
    }

    @Override
    public StringBuilder appendContent(StringBuilder buf, XMLFormatting fmt)
    {
        buf.append(CharDataConversion.encode(description));
        return buf;
    }

    /*
     * The XML Stag for XML encoding
     */
    private static final String XMLTAG = "warningFeedback";

    /**
     * The XML Stag for XML encoding -- use this static method when you want to
     * see if a given String equals the xml tag for this class
     */
    public static String xmlTag()
    {
        return XMLTAG;
    }

    /**
     * The XML Stag for XML encoding -- use this method to find out the run-time
     * xml tag of an object
     */
    @Override
    public String getXMLTag()
    {
        return XMLTAG;
    }

    public void setCharacterId(String characterId)
    {
        this.characterId = characterId;
    }
    
    public static final String BMLNAMESPACE = "http://www.bml-initiative.org/bml/bml-1.0";

    @Override
    public String getNamespace()
    {
        return BMLNAMESPACE;
    }
}
