package rnb.analyzer.nori.analyzer;

public class AnalyzingTerm {
	
	public static final String DEFAULT_TYPE = "word";
	
	//분리된 토큰
	private String token;
	//해당 토큰의 위치
	private int position;
	//해당 토큰의 시작 오프셋
	private int startOffset;
	//해당 토큰의 끝 오프셋
	private int endOffset;
	//토큰 타입, default 'word'
	private String tokenType = DEFAULT_TYPE;
	//토큰의 품사
	private String partOfSpeech;
	
	private String leftPOS;
	
	private String rightPOS;
	
	private String reading;
	
	public AnalyzingTerm() {}
	
	public AnalyzingTerm(String token, int position, int startOffset, int endOffset, String tokenType,
			String partOfSpeech, String leftPOS, String rightPOS, String reading) {
		super();
		this.token = token;
		this.position = position;
		this.startOffset = startOffset;
		this.endOffset = endOffset;
		this.tokenType = tokenType;
		this.partOfSpeech = partOfSpeech;
		this.leftPOS = leftPOS;
		this.rightPOS = rightPOS;
		this.reading = reading;
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getStartOffset() {
		return startOffset;
	}

	public void setStartOffset(int startOffset) {
		this.startOffset = startOffset;
	}

	public int getEndOffset() {
		return endOffset;
	}

	public void setEndOffset(int endOffset) {
		this.endOffset = endOffset;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public String getPartOfSpeech() {
		return partOfSpeech;
	}

	public void setPartOfSpeech(String partOfSpeech) {
		this.partOfSpeech = partOfSpeech;
	}

	public String getLeftPOS() {
		return leftPOS;
	}

	public void setLeftPOS(String leftPOS) {
		this.leftPOS = leftPOS;
	}

	public String getRightPOS() {
		return rightPOS;
	}

	public void setRightPOS(String rightPOS) {
		this.rightPOS = rightPOS;
	}

	public String getReading() {
		return reading;
	}

	public void setReading(String reading) {
		this.reading = reading;
	}

	@Override
	public String toString() {
		return "AnalyzingTerm [token=" + token + ", position=" + position + ", startOffset=" + startOffset
				+ ", endOffset=" + endOffset + ", tokenType=" + tokenType + ", partOfSpeech=" + partOfSpeech
				+ ", leftPOS=" + leftPOS + ", rightPOS=" + rightPOS + ", reading=" + reading + "]";
	}

}
