package rnb.analyzer.nori;
import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.ko.KoreanAnalyzer;
import org.apache.lucene.analysis.ko.KoreanPartOfSpeechStopFilter;
import org.apache.lucene.analysis.ko.KoreanReadingFormFilter;
import org.apache.lucene.analysis.ko.KoreanTokenizer;
import org.apache.lucene.analysis.ko.KoreanTokenizer.DecompoundMode;
import org.apache.lucene.analysis.ko.KoreanTokenizerFactory;
import org.apache.lucene.analysis.ko.tokenattributes.PartOfSpeechAttribute;
import org.apache.lucene.analysis.ko.tokenattributes.ReadingAttribute;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;

public class NoriAnalyzerTest {
	
	private static final String[] EXAMPLE_TEXT = {"알앤비소프트는 어떤 회사입니까?","오늘 날씨가 어떻게 되나요?","내일 점심 메뉴는 뭔가요?"};
	
//	private Analyzer analyzer = new KoreanAnalyzer();
	
	private Analyzer analyzer = new NoriAnalyzer();
	
	public void analyze(String[] exampleTexts) throws IOException {
		
		System.out.println("===================Nori Analyzer==================");
		System.out.println("\n");
		
		for(String text : exampleTexts) {
			
			System.out.printf("Example Text ::::::: {%s}\n",text);
			AnalyzerUtils.displayTokens(analyzer, text);
			System.out.println("=================================================");
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		NoriAnalyzerTest noriTest = new NoriAnalyzerTest();
		
		noriTest.analyze(noriTest.EXAMPLE_TEXT);
		
	}

}
class AnalyzerUtils{
	public static void displayTokens(Analyzer analyzer, String text) throws IOException {
		displayTokens(analyzer.tokenStream("content", new StringReader(text)));
	}
	
	public static void displayTokens(TokenStream stream) throws IOException {
		//텀 속성확인
		CharTermAttribute charAttr = stream.addAttribute(CharTermAttribute.class);
		
		//위치증가값 속성 확인
		PositionIncrementAttribute posAttr = stream.addAttribute(PositionIncrementAttribute.class);
		
		//오프셋 위치확인
		OffsetAttribute offsetAttr = stream.addAttribute(OffsetAttribute.class);
		
		//텀타입 속성 확인
		TypeAttribute typeAttr = stream.addAttribute(TypeAttribute.class);
		
		//Part of Speech 속성확인
		PartOfSpeechAttribute partAttr = stream.addAttribute(PartOfSpeechAttribute.class);
		
		//Reading 속성확인
		ReadingAttribute readingAttr = stream.addAttribute(ReadingAttribute.class);
		
		stream.reset();
		
		int position = 0;
		
		while(stream.incrementToken()) {
			int increment = posAttr.getPositionIncrement();
			
			position = position + increment;
			
			System.out.println();
			System.out.println("postion :::: "+position);
			System.out.println("term :::: "+charAttr.toString());
			System.out.println("offset :::: ["+offsetAttr.startOffset()+","+offsetAttr.endOffset()+"]");
			System.out.println("term type :::: "+typeAttr.type());
			System.out.println("part of speech :::: "+partAttr.getPOSType()+" ["+partAttr.getLeftPOS()+","+partAttr.getRightPOS()+"]");
			System.out.println("reading :::: "+readingAttr.getReading());
			System.out.println();
		}
		
		stream.end();
		stream.close();
	}
}

class NoriAnalyzer extends Analyzer{

	@Override
	protected TokenStreamComponents createComponents(String arg0) {
		// TODO Auto-generated method stub
		
		
		Tokenizer tokenizer = new KoreanTokenizer();
		
		
		TokenFilter filters = new KoreanPartOfSpeechStopFilter(tokenizer);
		filters = new KoreanReadingFormFilter(tokenizer);
		filters = new LowerCaseFilter(tokenizer);
		
		return new TokenStreamComponents(tokenizer, filters);
	}
	
}