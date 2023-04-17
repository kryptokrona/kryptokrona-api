<script>
  import { COLOR } from "../helpers/colors";
  import CircleChart from "../components/charts/CircleChart.svelte";
  import ToggleBox from "../components/toggle-box/ToggleBox.svelte";
  import TitleAndTextContainer from "../components/containers/TitleAndTextContainer.svelte";
  import Grid from "../components/grids/Grid.svelte";
  import {
    cpuUsageOverTime,
    ramUsageOverTime,
    diskUsageOverTime,
    cpuUsage,
    ramUsage,
    diskUsage,
    threads,
    posts,
    groupPosts,
    totalPosts,
    totalGroupPosts,
  } from "../stores/data";
  import { onMount, onDestroy } from "svelte";
  import {
    getCpuUsage,
    getRamUsage,
    getDiskUsage,
    getRamUsageOverTime,
    getCpuUsageOverTime,
    getDiskUsageOverTime,
    getThreads,
  } from "../api/prometheus";
  import { getPosts, getGroupPosts } from "../api/hugin";
  import TimeIntervalChart from "../components/charts/TimeIntervalChart.svelte";
  import {
    getLocaleString,
    getTwoDecimalsPercentage,
  } from "../helpers/helpers";
  export let data;

  $cpuUsage = data.prometheus.cpuUsage;
  $ramUsage = data.prometheus.ramUsage;
  $diskUsage = data.prometheus.diskUsage;
  $cpuUsageOverTime = data.prometheus.cpuUsageOverTime;
  $ramUsageOverTime = data.prometheus.ramUsageOverTime;
  $diskUsageOverTime = data.prometheus.diskUsageOverTime;
  $threads = data.prometheus.threads;
  $posts = data.posts;
  $groupPosts = data.groupPosts;
  $totalPosts = data.totalPosts;
  $totalGroupPosts = data.totalGroupPosts;

  let fiveSecondInterval;
  let oneSecondInterval;
  let oneMinInterval;
  let ramUsageOverTimeInterval = "1h";
  let cpuUsageOverTimeInterval = "1h";
  let diskUsageOverTimeInterval = "1h";
  let postsInterval = "1h";
  let groupPostsInterval = "1h";

  onMount(() => {
    fiveSecondInterval = setInterval(() => {
      setRamUsageOverTime();
      setCpuUsageOverTime();
      setDiskUsageOverTime();
    }, 1000 * 5);
    oneSecondInterval = setInterval(() => {
      setCpuUsage();
      setRamUsage();
      setDiskUsage();
      setThreads();
    }, 1000);
  });

  onDestroy(() => {
    clearInterval(oneSecondInterval);
    clearInterval(fiveSecondInterval);
    clearInterval(oneMinInterval);
  });

  async function setCpuUsage() {
    $cpuUsage = await getCpuUsage();
  }
  async function setRamUsage() {
    $ramUsage = await getRamUsage();
  }
  async function setDiskUsage() {
    $diskUsage = await getDiskUsage();
  }
  async function setCpuUsageOverTime(event) {
    if (event) cpuUsageOverTimeInterval = event.detail.time;
    $cpuUsageOverTime = await getCpuUsageOverTime(cpuUsageOverTimeInterval);
  }
  async function setRamUsageOverTime(event) {
    if (event) ramUsageOverTimeInterval = event.detail.time;
    $ramUsageOverTime = await getRamUsageOverTime(ramUsageOverTimeInterval);
  }
  async function setDiskUsageOverTime(event) {
    if (event) diskUsageOverTimeInterval = event.detail.time;
    $diskUsageOverTime = await getDiskUsageOverTime(diskUsageOverTimeInterval);
  }
  async function setThreads() {
    $threads = await getThreads();
  }

  async function setPostsOverTime(event) {
    if (event) postsInterval = event.detail.time;
    $posts = {};
    $posts = await getPosts(postsInterval);
  }
  async function setGroupPostsOverTime(event) {
    if (event) groupPostsInterval = event.detail.time;
    $groupPosts = {};
    $groupPosts = await getGroupPosts(groupPostsInterval);
  }
</script>

<ToggleBox title={"Quick overview"}>
  <Grid columns={5}>
    <TitleAndTextContainer
      title="Threads"
      text={$threads.toString()}
      bigText={true}
    />
    <TitleAndTextContainer
      title="Uptime"
      text={data.prometheus.uptime.toFixed(0) + " Days"}
      bigText={true}
    />
    <TitleAndTextContainer title="CPU usage" text={$cpuUsage.toFixed(0) + "%"}>
      <div
        class="flex h-5/6 items-end justify-center absolute top-7 left-0 right-0"
      >
        <CircleChart data={[$cpuUsage]} id="cpuUsage" />
      </div>
    </TitleAndTextContainer>
    <TitleAndTextContainer title="RAM usage" text={$ramUsage.toFixed(0) + "%"}>
      <div
        class="flex h-5/6 items-end justify-center absolute top-7 left-0 right-0"
      >
        <CircleChart data={[$ramUsage]} id="ramUsage" />
      </div>
    </TitleAndTextContainer>
    <TitleAndTextContainer
      title="DISK usage"
      text={$diskUsage.toFixed(0) + "%"}
    >
      <div
        class="flex h-5/6 items-end justify-center absolute top-7 left-0 right-0"
      >
        <CircleChart data={[$diskUsage]} id="diskUsage" />
      </div>
    </TitleAndTextContainer>
  </Grid>
</ToggleBox>

<div class="mt-8" />

<ToggleBox title={"Server activity"}>
  <Grid columns={3} gridClass="md-grid">
    <TimeIntervalChart
      data={[{ data: $cpuUsageOverTime.values, name: "CPU" }]}
      labels={$cpuUsageOverTime.times}
      id="cpuUsageOverTime"
      title="CPU usage"
      color={COLOR.BLUE}
      xFormatter={getLocaleString}
      yFormatter={getTwoDecimalsPercentage}
      on:updateTimeInterval={setCpuUsageOverTime}
    />
    <TimeIntervalChart
      data={[{ data: $ramUsageOverTime.values, name: "RAM" }]}
      labels={$ramUsageOverTime.times}
      id="ramUsageOverTime"
      title="RAM usage"
      color={COLOR.BLUE}
      xFormatter={getLocaleString}
      yFormatter={getTwoDecimalsPercentage}
      on:updateTimeInterval={setRamUsageOverTime}
    />
    <TimeIntervalChart
      data={[{ data: $diskUsageOverTime.values, name: "DISK" }]}
      labels={$diskUsageOverTime.times}
      id="diskUsageOverTime"
      title="DISK usage"
      color={COLOR.BLUE}
      xFormatter={getLocaleString}
      yFormatter={getTwoDecimalsPercentage}
      on:updateTimeInterval={setDiskUsageOverTime}
    />
  </Grid>
</ToggleBox>

<div class="mt-8" />

<ToggleBox title="Hugin activity">
  <Grid columns={4} gridClass="sm-grid">
    {#if $posts.values}
      <TimeIntervalChart
        data={[{ data: $posts.values, name: "Private messages" }]}
        labels={$posts.times}
        id="posts"
        title="Private messages"
        color={COLOR.VIOLET}
        includeYear="true"
        activeInterval={postsInterval}
        type="bar"
        on:updateTimeInterval={setPostsOverTime}
      />
    {:else}
      <TitleAndTextContainer title="Private messages">
        <div class="w-full h-full flex justify-center">
          <div class="text-3xl pt-16">
            <i class="fa-solid fa-spinner fa-spin-pulse" />
          </div>
        </div>
      </TitleAndTextContainer>
    {/if}
    {#if $groupPosts.values}
      <TimeIntervalChart
        data={[{ data: $groupPosts.values, name: "Group messages" }]}
        labels={$groupPosts.times}
        id="groupPosts"
        title="Group messages"
        color={COLOR.VIOLET}
        includeYear="true"
        activeInterval={groupPostsInterval}
        type="bar"
        on:updateTimeInterval={setGroupPostsOverTime}
      />
    {:else}
      <TitleAndTextContainer title="Group messages">
        <div class="w-full h-full flex justify-center">
          <div class="text-3xl pt-16">
            <i class="fa-solid fa-spinner fa-spin-pulse" />
          </div>
        </div>
      </TitleAndTextContainer>
    {/if}
    <TitleAndTextContainer
      title="Total private messages 1 year"
      text={$totalPosts}
      bigText={true}
    />
    <TitleAndTextContainer
      title="Total Group messages 1 year"
      text={$totalGroupPosts}
      bigText={true}
    />
  </Grid>
</ToggleBox>

<div class="mt-8" />
