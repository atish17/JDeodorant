package gr.uom.java.ast.decomposition;

import java.util.ArrayList;
import java.util.List;

public class CatchClauseObject {
	
	private List<AbstractStatement> statementList;
	private List<AbstractExpression> expressionList;
	private String exceptionType;
	private TryStatementObject parent;
	
	public CatchClauseObject() {
		this.statementList = new ArrayList<AbstractStatement>();
		this.expressionList = new ArrayList<AbstractExpression>();
		this.parent = null;
	}

    public void setParent(TryStatementObject parent) {
    	this.parent = parent;
    }

	public TryStatementObject getParent() {
		return parent;
	}

	public void addStatement(AbstractStatement statement) {
		statementList.add(statement);
	}

	public List<AbstractStatement> getStatements() {
		return statementList;
	}

	public void addExpression(AbstractExpression expression) {
		expressionList.add(expression);
	}

	public List<AbstractExpression> getExpressions() {
		return expressionList;
	}

	public String getExceptionType() {
		return exceptionType;
	}

	public void setExceptionType(String exceptionType) {
		this.exceptionType = exceptionType;
	}

	public List<String> stringRepresentation() {
		List<String> stringRepresentation = new ArrayList<String>();
		stringRepresentation.add(this.toString());
		for(AbstractStatement statement : statementList) {
			stringRepresentation.addAll(statement.stringRepresentation());
		}
		return stringRepresentation;
	}

	public List<CompositeStatementObject> getIfStatements() {
		List<CompositeStatementObject> ifStatements = new ArrayList<CompositeStatementObject>();
		for(AbstractStatement statement : statementList) {
			if(statement instanceof CompositeStatementObject) {
				CompositeStatementObject composite = (CompositeStatementObject)statement;
				ifStatements.addAll(composite.getIfStatements());
			}
		}
		return ifStatements;
	}

	public List<CompositeStatementObject> getSwitchStatements() {
		List<CompositeStatementObject> switchStatements = new ArrayList<CompositeStatementObject>();
		for(AbstractStatement statement : statementList) {
			if(statement instanceof CompositeStatementObject) {
				CompositeStatementObject composite = (CompositeStatementObject)statement;
				switchStatements.addAll(composite.getSwitchStatements());
			}
		}
		return switchStatements;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("catch");
		if(expressionList.size() > 0) {
			sb.append("(");
			sb.append(exceptionType).append(" ");
			for(AbstractExpression expression : expressionList)
				sb.append(expression.toString());
			sb.append(")");
		}
		return sb.toString();
	}
}
