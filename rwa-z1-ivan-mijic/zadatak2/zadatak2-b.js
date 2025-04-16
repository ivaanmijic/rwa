function TimeCache(expiration) {
  this.expiration = expiration * 1000;
  this.cache = {};
}

TimeCache.prototype.put = function (cacheKey, data) {
  var now = Date.now();
  this.cache[cacheKey] = {
    data: data,
    expiresAt: now + this.expiration,
  };
};

TimeCache.prototype.get = function (cacheKey) {
  var entry = this.cache[cacheKey];

  if (!entry) {
    return null;
  }

  var now = Date.now();
  if (now > entry.expiresAt) {
    delete this.cache[cacheKey];
    return null;
  }

  return entry.data;
};

TimeCache.prototype.clear = function () {
  this.cache = {};
};

// MARK: - Test

var cache = new TimeCache(3);

cache.put("key1", "data1");
console.log(cache.get("key1"));

setTimeout(() => {
  console.log(cache.get("key1"));
}, 4000);
