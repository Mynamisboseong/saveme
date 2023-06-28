import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class Manager {
    // 이름과 점수를 종합
    public Schedule schedule = new Schedule();
    public Date year = new Date();
    public Date month = new Date();
    public Date day = new Date();

    public Manager(String schedule, int year, int month, int day) {
        this.schedule.setSchedule(schedule);
        this.year.setDate(year);
        this.month.setDate(month);
        this.day.setDate(day);
    }

    public void setSchedule(String schedule) {
        this.schedule.setSchedule(schedule);
    }

    public String getSchedule() {
        return this.schedule.getSchedule();
    }

    public void setYear(int date) {
        this.year.setDate(date);
    }

    public int getYear() {
        return this.year.getDate();
    }

    public void setMonth(int date) {
        this.month.setDate(date);
    }

    public int getMonth() {
        return this.month.getDate();
    }

    public void setDay(int date) {
        this.day.setDate(date);
    }

    public int getDay() {
        return this.day.getDate();
    }

    @Override
    public String toString() {
        return "스케줄 : " + getSchedule() + " " + getYear() + "년 " + getMonth() + "월 " + getDay() + "일 " + "\n";
    }
}

class Schedule {
    String schedule;

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getSchedule() {
        return schedule;
    }
}

class Date {
    int date;

    public void setDate(int date) {
        this.date = date;
    }

    public int getDate() {
        return date;
    }
}

public class Project extends JFrame implements ActionListener {
    private JButton btn2 = new JButton("일정 추가");
    private JButton btn3 = new JButton("일정 삭제");
    private JButton btn4 = new JButton("일정 검색");
    private JButton btn5 = new JButton("일정 수정");
    private JButton btn6 = new JButton("일정 출력");
    private JButton btn7 = new JButton("모두 지우기");
    private JTextField tf1 = new JTextField("");
    private JTextArea ta = new JTextArea();
    ArrayList<Manager> list = new ArrayList<Manager>();

    Project() {
        // JFrame 설정
        setTitle("일정 캘린더");
        setSize(600, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);

        // 버튼 및 텍스트 필드 설정
        btn2.setBounds(440, 10, 120, 25);
        add(btn2);
        btn3.setBounds(440, 50, 120, 25);
        add(btn3);
        btn4.setBounds(440, 90, 120, 25);
        add(btn4);
        btn5.setBounds(440, 130, 120, 25);
        add(btn5);
        btn6.setBounds(440, 170, 120, 25);
        add(btn6);
        btn7.setBounds(440, 210, 120, 25);
        add(btn7);

        tf1.setBounds(30, 10, 400, 25);
        add(tf1);
        ta.setBounds(30, 50, 400, 400);
        add(ta);

        // 버튼에 이벤트 리스너 등록
        btn2.addActionListener(this);
        btn3.addActionListener(this);
        btn4.addActionListener(this);
        btn5.addActionListener(this);
        btn6.addActionListener(this);
        btn7.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn2) {
            // 일정 추가 버튼 클릭 시
            String[] temp = tf1.getText().split(" ");
            int year = Integer.parseInt(temp[1]);
            int month = Integer.parseInt(temp[2]);
            int day = Integer.parseInt(temp[3]);
            list.add(new Manager(temp[0], year, month, day));
            ta.append(tf1.getText() + "\n");
        } else if (e.getSource() == btn3) {
            // 일정 삭제 버튼 클릭 시
            String schedule2 = tf1.getText();
            Iterator<Manager> it2 = list.iterator();
            while (it2.hasNext()) {
                Manager temp = it2.next();
                if (temp.getSchedule().equals(schedule2)) {
                    it2.remove();
                    ta.append("해당 일정이 삭제되었습니다.\n"); // 일정이 삭제되었다는 메시지 출력
                }
            }
        } else if (e.getSource() == btn4) {
            // 일정 검색 버튼 클릭 시
            String keyword = tf1.getText();
            searchSchedule(keyword);
        } else if (e.getSource() == btn5) {
            // 일정 수정 버튼 클릭 시
            String[] schedule1 = tf1.getText().split(" ");
            int year = Integer.parseInt(schedule1[1]);
            int month = Integer.parseInt(schedule1[2]);
            int day = Integer.parseInt(schedule1[3]);
            Iterator<Manager> it1 = list.iterator();
            while (it1.hasNext()) {
                Manager temp = it1.next();
                if (temp.getSchedule().equals(schedule1[0])) {
                    temp.setYear(year);
                    temp.setMonth(month);
                    temp.setDay(day);
                }
            }
        } else if (e.getSource() == btn6) {
            // 일정 출력 버튼 클릭 시
            for (Manager manager : list) {
                ta.append(manager.toString());
            }
        } else if (e.getSource() == btn7) {
            // 모두 지우기 버튼 클릭 시
            ta.setText("");
        }
    }

    public void searchSchedule(String keyword) {
        boolean found = false;
        ta.setText(""); // 이전에 출력된 내용 지우기

        for (Manager manager : list) {
            if (manager.getSchedule().equals(keyword)) {
                ta.append(manager.toString());
                found = true;
            }
        }

        if (!found) {
            ta.append("해당 일정을 찾을 수 없습니다.\n");
        }
    }

    public static void main(String[] args) {
        Project app = new Project();
    }
}

