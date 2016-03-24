package de.uni_konstanz.knime.scala.myfirst;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;

import org.knime.base.data.append.column.AppendedCellFactory;
import org.knime.core.data.DataCell;
import org.knime.core.data.DataColumnSpec;
import org.knime.core.data.DataColumnSpecCreator;
import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.RowKey;
import org.knime.core.data.container.CellFactory;
import org.knime.core.data.container.ColumnRearranger;
import org.knime.core.data.def.DefaultRow;
import org.knime.core.data.def.DoubleCell;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.CanceledExecutionException;
import org.knime.core.node.defaultnodesettings.SettingsModelIntegerBounded;
import org.knime.core.node.defaultnodesettings.SettingsModelString;

import scala.math.BigInt;

import org.knime.core.node.ExecutionContext;
import org.knime.core.node.ExecutionMonitor;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.NodeLogger;
import org.knime.core.node.NodeModel;
import org.knime.core.node.NodeSettingsRO;
import org.knime.core.node.NodeSettingsWO;

/**
 * This is the model implementation of MyFirstScalaKnimeNode.
 * 
 *
 * @author Oliver Sampson, University of Konstanz
 */
public class MyFirstScalaKnimeNodeNodeModel extends NodeModel {

	// the logger instance
	private static final NodeLogger logger = NodeLogger.getLogger(MyFirstScalaKnimeNodeNodeModel.class);

	private static final String CFGKEY_FACTOR = "Factor";

	public static final int PORT_IN_DATA = 0;

	private SettingsModelString m_factorColumn = createSettingsModelFactorColumn();

	/**
	 * Constructor for the node model.
	 */
	protected MyFirstScalaKnimeNodeNodeModel() {

		// TODO one incoming port and one outgoing port is assumed
		super(1, 1);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected BufferedDataTable[] execute(final BufferedDataTable[] inData, final ExecutionContext exec)
			throws Exception {

		ColumnRearranger c = new ColumnRearranger(inData[PORT_IN_DATA].getDataTableSpec());
		MyFirstScalaNodeCellFactory f = new MyFirstScalaNodeCellFactory(
				inData[PORT_IN_DATA].getDataTableSpec().findColumnIndex(m_factorColumn.getStringValue()), exec);

		c.append(f);

		final BufferedDataTable returnVal = exec.createColumnRearrangeTable(inData[PORT_IN_DATA], c, exec);

		return new BufferedDataTable[] { returnVal };
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void reset() {
		// TODO Code executed on reset.
		// Models build during execute are cleared here.
		// Also data handled in load/saveInternals will be erased here.
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected DataTableSpec[] configure(final DataTableSpec[] inSpecs) throws InvalidSettingsException {

		// TODO: check if user settings are available, fit to the incoming
		// table structure, and the incoming types are feasible for the node
		// to execute. If the node can execute in its current state return
		// the spec of its output data table(s) (if you can, otherwise an array
		// with null elements), or throw an exception with a useful user message

		return new DataTableSpec[] { null };
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void saveSettingsTo(final NodeSettingsWO settings) {

		// save user settings to the config object.

		m_factorColumn.saveSettingsTo(settings);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void loadValidatedSettingsFrom(final NodeSettingsRO settings) throws InvalidSettingsException {

		// load (valid) settings from the config object.
		// It can be safely assumed that the settings are valided by the
		// method below.

		m_factorColumn.loadSettingsFrom(settings);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void validateSettings(final NodeSettingsRO settings) throws InvalidSettingsException {

		// check if the settings could be applied to our model
		// e.g. if the count is in a certain range (which is ensured by the
		// SettingsModel).
		// Do not actually set any values of any member variables.

		m_factorColumn.validateSettings(settings);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void loadInternals(final File internDir, final ExecutionMonitor exec)
			throws IOException, CanceledExecutionException {

		// TODO load internal data.
		// Everything handed to output ports is loaded automatically (data
		// returned by the execute method, models loaded in loadModelContent,
		// and user settings set through loadSettingsFrom - is all taken care
		// of). Load here only the other internals that need to be restored
		// (e.g. data used by the views).

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void saveInternals(final File internDir, final ExecutionMonitor exec)
			throws IOException, CanceledExecutionException {

		// TODO save internal models.
		// Everything written to output ports is saved automatically (data
		// returned by the execute method, models saved in the saveModelContent,
		// and user settings saved through saveSettingsTo - is all taken care
		// of). Save here only the other internals that need to be preserved
		// (e.g. data used by the views).

	}

	public static SettingsModelString createSettingsModelFactorColumn() {
		return new SettingsModelString(CFGKEY_FACTOR, "");
	}

	private class MyFirstScalaNodeCellFactory implements CellFactory, AppendedCellFactory {

		private int m_factorColumnIdx;
		private ExecutionContext m_exec;

		public MyFirstScalaNodeCellFactory(int factorColumnIdx, ExecutionContext exec) {
			m_factorColumnIdx = factorColumnIdx;
			m_exec = exec;
		}

		@Override
		public DataCell[] getAppendedCell(DataRow row) {
			return getCells(row);
		}

		@Override
		public DataCell[] getCells(DataRow row) {
			BigIntFactorial bif = new BigIntFactorial();
			int f = ((IntCell)row.getCell(m_factorColumnIdx)).getIntValue();
			
			BigInt i = bif.calcFactorial(f);
			
			return new DataCell[] {new StringCell(i.toString())};
		}

		@Override
		public DataColumnSpec[] getColumnSpecs() {
			return new DataColumnSpec[] { new DataColumnSpecCreator("Factorial", StringCell.TYPE).createSpec() };
		}

		@Override
		public void setProgress(int curRowNr, int rowCount, RowKey lastKey, ExecutionMonitor exec) {
			m_exec.setProgress((double) curRowNr / rowCount);
		}

	}

}
