package sv;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Interval {
	int start;
	int end;

	Interval() {
		start = 0;
		end = 0;
	}

	Interval(int s, int e) {
		start = s;
		end = e;
	}

	@Override
	public String toString() {
		return "[" + start + ", " + end + "]";
	}
}

public class IntervelQ {
	public static void main(String[] args) {
		Interval i1 = new Interval(1,2);
		Interval i2 = new Interval(3,5);
		Interval i3 = new Interval(6,7);
		Interval i4 = new Interval(8,10);		
		Interval i5 = new Interval(12,16);
		Interval i6 = new Interval(16,78);
		List<Interval> intervals = new ArrayList<Interval>();
		intervals.add(i1);
		intervals.add(i2);
		intervals.add(i3);
		intervals.add(i4);
		intervals.add(i5);
		System.out.println(insert(intervals, i6));

	}
	
	public int minMeetingRooms(Interval[] intervals) {
	    if (intervals == null || intervals.length == 0)
	        return 0;

	    // Sort the intervals by start time
	    Arrays.sort(intervals, new Comparator<Interval>() {
	        public int compare(Interval a, Interval b) { return a.start - b.start; }
	    });

	    // Use a min heap to track the minimum end time of merged intervals
	    PriorityQueue<Interval> heap = new PriorityQueue<Interval>(intervals.length, new Comparator<Interval>() {
	        public int compare(Interval a, Interval b) { return a.end - b.end; }
	    });

	    // start with the first meeting, put it to a meeting room
	    heap.offer(intervals[0]);

	    for (int i = 1; i < intervals.length; i++) {
	        // get the meeting room that finishes earliest
	        Interval interval = heap.poll();

	        if (intervals[i].start >= interval.end) {
	            // if the current meeting starts right after 
	            // there's no need for a new room, merge the interval
	            interval.end = intervals[i].end;
	        } else {
	            // otherwise, this meeting needs a new room
	            heap.offer(intervals[i]);
	        }

	        // don't forget to put the meeting room back
	        heap.offer(interval);
	    }

	    return heap.size();
	}
	
	
	public boolean canAttendMeetings(Interval[] intervals){
		Comparator<Interval> comp = new Comparator<Interval>() {
			@Override
			public int compare(Interval i1, Interval i2) {
				// TODO Auto-generated method stub
				return i1.start - i2.start;
			}
		};
		
		Arrays.sort(intervals, comp);
		
		for(int i = 1;i<intervals.length;i++){
			Interval i1 = intervals[i-1];
			Interval i2 = intervals[i];
			if(i1.end > i2.end){
				return false;
			}
		}
		
		return true;		
	}

	public static List<Interval> insert(List<Interval> intervals, Interval newInterval) {
		List<Interval> res = new ArrayList<Interval>();
		if (intervals.size() == 0) {
			res.add(newInterval);
			return res;
		}
		
		

		int point = 0;
		int left = newInterval.start;
		int right = newInterval.end;

		if(left > intervals.get(intervals.size()-1).end){
			intervals.add(newInterval);
			return intervals;
		}
		
		if(right < intervals.get(0).start){
			res.add(newInterval);
			res.addAll(intervals);
			return res;
		}
		
		while (point < intervals.size()) {
			if (left < intervals.get(point).start) {
				break;
			} else if (left >= intervals.get(point).start
					&& left <= intervals.get(point).end) {
				left = intervals.get(point).start;
				break;
			} else {
				res.add(intervals.get(point));
				point++;
			}
		}

		while (point < intervals.size()) {
			if (right < intervals.get(point).start) {
				res.add(new Interval(left, right));
				break;
			} else if (right >= intervals.get(point).start
					&& right <= intervals.get(point).end) {
				right = intervals.get(point).end;
				res.add(new Interval(left, right));
				point++;
				break;
			} else {
				if (point == intervals.size() - 1) {
					res.add(new Interval(left, right));
					return res;
				}
				point++;
			}
		}

		while (point < intervals.size()) {
			res.add(intervals.get(point));
			point++;
		}
		return res;

	}

	public static List<Interval> merge(List<Interval> intervals) {
		Comparator<Interval> comp = new Comparator<Interval>() {
			@Override
			public int compare(Interval i1, Interval i2) {
				// TODO Auto-generated method stub
				return i1.start - i2.start;
			}
		};

		Collections.sort(intervals, comp);
		List<Interval> res = new ArrayList<Interval>();

		int point = 1;
		int start = intervals.get(0).start;
		int end = intervals.get(0).end;
		while (point < intervals.size()) {
			if (end >= intervals.get(point).start) {
				end = Math.max(end, intervals.get(point).end);
			} else {
				Interval i = new Interval(start, end);
				res.add(i);
				start = intervals.get(point).start;
				end = intervals.get(point).end;
			}
			point++;
		}

		Interval i = new Interval(start, end);
		res.add(i);

		return res;

	}
}
