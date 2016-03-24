package de.uni_konstanz.knime.scala.myfirst;

import org.knime.core.data.IntValue;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentColumnNameSelection;

/**
 * <code>NodeDialog</code> for the "MyFirstScalaKnimeNode" Node.
 * 
 *
 * This node dialog derives from {@link DefaultNodeSettingsPane} which allows
 * creation of a simple dialog with standard components. If you need a more 
 * complex dialog please derive directly from 
 * {@link org.knime.core.node.NodeDialogPane}.
 * 
 * @author Oliver Sampson, University of Konstanz
 */
public class MyFirstScalaKnimeNodeNodeDialog extends DefaultNodeSettingsPane {

    /**
     * New pane for configuring MyFirstScalaKnimeNode node dialog.
     * This is just a suggestion to demonstrate possible default dialog
     * components.
     */
    @SuppressWarnings("unchecked")
	protected MyFirstScalaKnimeNodeNodeDialog() {
        super();
        
        addDialogComponent(new DialogComponentColumnNameSelection(
                MyFirstScalaKnimeNodeNodeModel.createSettingsModelFactorColumn(),
                "Factor Column:", MyFirstScalaKnimeNodeNodeModel.PORT_IN_DATA,
                IntValue.class));
                    
    }
}

