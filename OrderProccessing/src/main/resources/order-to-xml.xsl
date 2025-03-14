<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml" indent="yes"/>
    
    <xsl:template match="/Order">
        <order>
            <orderId><xsl:value-of select="orderId"/></orderId>
            
            <customer><xsl:value-of select="customer"/></customer>
            
            <items>
                <xsl:for-each select="items/items">
                    <item>
                        <name><xsl:value-of select="name"/></name>
                        <price><xsl:value-of select="price"/></price>
                    </item>
                </xsl:for-each>
            </items>
        </order>
    </xsl:template>

</xsl:stylesheet>
