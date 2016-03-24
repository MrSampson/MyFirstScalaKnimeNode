package de.uni_konstanz.knime.scala.myfirst;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;

/**
 * <code>NodeFactory</code> for the "MyFirstScalaKnimeNode" Node.
 * 
 *
 * @author University of Konstanz
 */
public class MyFirstScalaKnimeNodeNodeFactory 
        extends NodeFactory<MyFirstScalaKnimeNodeNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public MyFirstScalaKnimeNodeNodeModel createNodeModel() {
        return new MyFirstScalaKnimeNodeNodeModel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNrNodeViews() {
        return 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeView<MyFirstScalaKnimeNodeNodeModel> createNodeView(final int viewIndex,
            final MyFirstScalaKnimeNodeNodeModel nodeModel) {
        return new MyFirstScalaKnimeNodeNodeView(nodeModel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasDialog() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeDialogPane createNodeDialogPane() {
        return new MyFirstScalaKnimeNodeNodeDialog();
    }

}

