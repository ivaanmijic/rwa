function f1(f2, p) {
  let lastCallTime = 0;
  let timeoutId = null;

  return function f3(...args) {
    let now = Date.now();
    let sinceLastCall = now - lastCallTime;
    let remainingTime = p - sinceLastCall;

    if (remainingTime <= 0) {
      if (timeoutId) {
        clearTimeout(timeoutId);
        timeoutId = null;
      }
      lastCallTime = now;
      f2(...args);
    } else if (!timeoutId) {
      timeoutId = setTimeout(() => {
        lastCallTime = Date.now();
        timeoutId = null;
        f2(...args);
      }, remainingTime);
    }
  };
}

// MARK: - Test

let f3 = f1(() => {
  console.log("Executed at ", new Date().toLocaleTimeString());
}, 3000);

console.log("Called at ", new Date().toLocaleTimeString());
f3();
console.log("Called at ", new Date().toLocaleTimeString());
f3();
