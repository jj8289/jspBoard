package board.bean;

// ?���? 묻는 질문
public class FAQboard {
	int no;				// �? 번호
	String subject;		// �? ?���?
	String writeDate;	// ?��?��?��
	int ref;
	int readCount;		// 조회?��
	String answer;		// 질문?�� ???�� ?���?
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readcount) {
		this.readCount = readcount;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
}
